/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
package oracle.official.Demo.io.basic.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

/**
 * http://docs.oracle.com/javase/tutorial/essential/io/charstreams.html<br>
 * Descript:
 * The Java platform stores character values using Unicode conventions. Character stream I/O automatically translates 
 * this internal format to and from the local character set. In Western locales, the local character set is usually an 8-bit
 * superset of ASCII.
 * <br>
 * For most applications, I/O with character streams is no more complicated than I/O with byte streams. Input and 
 * output done with stream classes automatically translates to and from the local character set. A program that uses 
 * character streams in place of byte streams automatically adapts to the local character set and is ready for
 * internationalization — all without extra effort by the programmer. 
 * <br>
 * If internationalization isn't a priority, you can simply use the character stream classes without paying much attention 
 * to character set issues. Later, if internationalization becomes a priority, your program can be adapted without 
 * extensive recoding. See the Internationalization trail for more information.
 * <br>
 * Copyright: Copyright(c) Aug 27, 2014<br>
 * Encoding:UNIX UTF-8
 *
 */
public class CharacterStreams {
	
	/**
	 * 
	 * Descript:
	 * All character stream classes are descended from Reader and Writer. As with byte streams, there are character 
	 * stream classes that specialize in file I/O: FileReader and FileWriter. The CopyCharacters example 
	 * illustrates these classes.
	 * <br>
	 * Copyright: Copyright(c) Aug 27, 2014<br>
	 * Encoding:UNIX UTF-8
	 *
	 */
	public static class CopyCharacters {
	    public static void main(String[] args) throws IOException {

	        FileReader inputStream = null;
	        FileWriter outputStream = null;

	        try {
	            inputStream = new FileReader("xanadu.txt");
	            outputStream = new FileWriter("characteroutput.txt");

	            int c;
	            while ((c = inputStream.read()) != -1) {
	                outputStream.write(c);
	            }
	        } finally {
	            if (inputStream != null) {
	                inputStream.close();
	            }
	            if (outputStream != null) {
	                outputStream.close();
	            }
	        }
	    }
	}
	
	/**
	 * 
	 * Descript:<br>
	 * Copyright: Copyright(c) Aug 27, 2014<br>
	 * Encoding:UNIX UTF-8
	 *
	 */
	public static class CopyLines {
	    public static void main(String[] args) throws IOException {

	        BufferedReader inputStream = null;
	        PrintWriter outputStream = null;

	        try {
	            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
	            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));

	            String l;
	            while ((l = inputStream.readLine()) != null) {
	                outputStream.println(l);
	            }
	        } finally {
	            if (inputStream != null) {
	                inputStream.close();
	            }
	            if (outputStream != null) {
	                outputStream.close();
	            }
	        }
	    }
	}
	
	@SuppressWarnings("unused")
	@Test
	public void characterStreamUseByteStream(){
		InputStream inputStream = new ByteArrayInputStream("123".getBytes());
		OutputStream outputStream = new ByteArrayOutputStream();
		
		Reader reader = new InputStreamReader(inputStream);
		Writer writer = new OutputStreamWriter(outputStream);
	}
}
