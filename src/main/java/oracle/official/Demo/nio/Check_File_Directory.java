package oracle.official.Demo.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Check_File_Directory {

    private volatile URL url;

    @Before
    public void before() {
        this.url =
            Thread.currentThread().getContextClassLoader()
                .getResource("oracle/official/Demo/nio/Check_File_Directory.class");
    }

    @Test
    public void checkAccessibility() throws URISyntaxException {
        Path path = Paths.get(this.url.toURI());

        Assert.assertTrue(Files.isRegularFile(path));
        Assert.assertTrue(Files.isReadable(path));
        Assert.assertTrue(Files.isWritable(path));
        Assert.assertTrue(Files.isExecutable(path));
    }

    @Test
    public void comparat() throws URISyntaxException , IOException {
        Path p1 = Paths.get(this.url.toURI());
        Path p2 = Paths.get(this.url.toURI());

        Assert.assertTrue(Files.isSameFile(p1 , p2));
    }
}
