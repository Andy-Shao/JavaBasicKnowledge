package oracle.official.Demo.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

public class LinksSymbolic {
	
	private URL url = Thread.currentThread().getContextClassLoader()
			.getResource("oracle/official/Demo/nio/LinksSymbolic.class");

	@Ignore
	@Test
	public  void symbolicLink() throws URISyntaxException, IOException{
		Path target = Paths.get(this.url.toURI());
		Path newLink = Paths.get("symbolic_links");
		
		try{
			Files.createSymbolicLink(newLink, target);
		} finally{
			Files.deleteIfExists(newLink);
		}
		
	}
	
	@Test
	public void hardLink() throws URISyntaxException, IOException{
		Path newLink = createHardLink();
		Files.deleteIfExists(newLink);
	}
	
	public Path createHardLink() throws URISyntaxException, IOException{
		Path newLink = Paths.get("hard_link");
		Path existingFile = Paths.get(this.url.toURI());
		
		return Files.createLink(newLink, existingFile);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void findingTargetFromLink() throws URISyntaxException, IOException{
		Path newLink = createHardLink();
		byte[] bs = Files.readAllBytes(newLink);
		Files.deleteIfExists(newLink);
	}
}
