package oracle.official.Demo.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UsefulMethods {

	@Test
	public void getMIME_Type() throws IOException, URISyntaxException{
		URI uri = Thread.currentThread().getContextClassLoader()
				.getResource("oracle/official/Demo/nio/UsefulMethods.class").toURI();
		String type = Files.probeContentType(Paths.get(uri));

		assertNull(type);
	}
	
	@Test
	public void pathStringSeparator(){
		String separator = FileSystems.getDefault().getSeparator();
		
		assertThat(separator, is(File.separator));
	}
	
	@Test
	public void fileStore() throws URISyntaxException{
		for(FileStore store : FileSystems.getDefault().getFileStores()){
			System.out.println(store);
		}
	}
}
