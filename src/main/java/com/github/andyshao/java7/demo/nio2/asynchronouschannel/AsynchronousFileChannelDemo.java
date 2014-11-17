package com.github.andyshao.java7.demo.nio2.asynchronouschannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Descript:General asynchronous file channel.<br>
 * Copyright: Copyright(c) Jul 31, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 * @see AsynchronousFileChannel
 *
 */
public class AsynchronousFileChannelDemo {

	private static final Path FILE_PATH = Paths.get("large.bin");

	@Test
	public void asyncWriter() throws InterruptedException, ExecutionException, IOException{
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(FILE_PATH, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(23 * 1024 * 1024);
		Future<Integer> result = channel.write(buffer, 0);
		
		//Other action
		result.get();
	}
	
	@Before
	@After
	public void beforeOrAfter() throws IOException{
		Files.deleteIfExists(FILE_PATH);
	}
}
