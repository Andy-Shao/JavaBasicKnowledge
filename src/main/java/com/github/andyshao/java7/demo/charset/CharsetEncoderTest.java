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

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import com.github.andyshao.convert.Convert;
import com.github.andyshao.util.ArrayTools;
import com.github.andyshao.util.ByteBuffereTools;

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
        assertThat(Convert.BYTES_2_HEX.convert(ArrayTools.pack_unpack(ByteBuffereTools.usedArray(outputBuffer) ,
            Byte[].class)) , is("48656c6c6f"));
    }

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
                    if (result.isOverflow()) writeToChannel(destChannel , outputBuffer);
                    else if (result.isUnderflow()) break;
                }
            }

            writeToChannel(destChannel , outputBuffer);
            encoder.encode(CharBuffer.allocate(0) , outputBuffer , true);
            CoderResult result = encoder.flush(outputBuffer);
            if (result.isOverflow()) {
                ByteBuffer newBuffer = ByteBuffer.allocate(1024);
                encoder.flush(newBuffer);
                writeToChannel(destChannel , newBuffer);
            } else writeToChannel(destChannel , outputBuffer);
        }
    }

    private void writeToChannel(WritableByteChannel channel , ByteBuffer buffer) throws IOException {
        buffer.flip();
        channel.write(buffer);
        buffer.compact();
    }

    @Test
    public void useFilter() throws CharacterCodingException {
        assertThat(filter("你好，123世界!") , is("123!"));
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
}
