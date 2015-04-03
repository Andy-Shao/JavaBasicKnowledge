package com.github.andyshao.java7.demo.charset;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.github.andyshao.lang.Convert;
import com.github.andyshao.nio.ByteBuffereOperation;
import com.github.andyshao.reflect.ArrayOperation;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 5, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class CharsetEncoderTest {

    @Ignore
    @Test
    public void encodeFile() throws IOException {
        Charset charset = Charset.forName("GB18030");
        CharsetEncoder encoder = charset.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.IGNORE);
        encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
        ByteBuffer outputBuffer = ByteBuffer.allocate(128);
        List<String> lines = Files.readAllLines(Paths.get("test.htm") , StandardCharsets.UTF_8);

        try (
            FileChannel destChannel =
                FileChannel.open(Paths.get("result.htm") , StandardOpenOption.CREATE , StandardOpenOption.WRITE)) {
            for (String line : lines) {
                CharBuffer charBuffer = CharBuffer.wrap(line);
                while (true) {
                    CoderResult result = encoder.encode(charBuffer , outputBuffer , false);
                    if (result.isOverflow()) this.writeToChannel(destChannel , outputBuffer);
                    else if (result.isUnderflow()) break;
                }
            }

            this.writeToChannel(destChannel , outputBuffer);
            encoder.encode(CharBuffer.allocate(0) , outputBuffer , true);
            CoderResult result = encoder.flush(outputBuffer);
            if (result.isOverflow()) {
                ByteBuffer newBuffer = ByteBuffer.allocate(1024);
                encoder.flush(newBuffer);
                this.writeToChannel(destChannel , newBuffer);
            } else this.writeToChannel(destChannel , outputBuffer);
        }
    }

    private String filter(String string) throws CharacterCodingException {
        Charset charset = StandardCharsets.ISO_8859_1;
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
        CharBuffer buffer = CharBuffer.wrap(string);
        ByteBuffer byteBuffer = encoder.encode(buffer);
        CharBuffer result = decoder.decode(byteBuffer);
        return result.toString();
    }

    @Test
    public void simpleEncode() {
        Charset charset = StandardCharsets.UTF_8;
        CharsetEncoder encoder = charset.newEncoder();
        CharBuffer inputBuffer = CharBuffer.allocate(256);
        inputBuffer.put("Hello").flip();
        ByteBuffer outputBuffer = ByteBuffer.allocate(256);
        encoder.encode(inputBuffer , outputBuffer , true);
        encoder.flush(outputBuffer);

        outputBuffer.flip();
        Assert.assertThat(Convert.BYTES_2_HEX.convert(ArrayOperation.pack_unpack(
            ByteBuffereOperation.usedArray(outputBuffer) , Byte[].class)) , Matchers.is("48656c6c6f"));
    }

    @Test
    public void useFilter() throws CharacterCodingException {
        Assert.assertThat(this.filter("你好，123世界!") , Matchers.is("123!"));
    }

    private void writeToChannel(WritableByteChannel channel , ByteBuffer buffer) throws IOException {
        buffer.flip();
        channel.write(buffer);
        buffer.compact();
    }
}
