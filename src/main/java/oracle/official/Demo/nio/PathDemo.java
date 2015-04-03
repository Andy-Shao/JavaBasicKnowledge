package oracle.official.Demo.nio;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PathDemo {

    @Test
    public void comparingPaths() {
        Path path = Paths.get("/home/joe/foo");
        Path otherPath = Paths.get("home/sally/bar");
        Path beginning = Paths.get("/home");
        Path ending = Paths.get("foo");

        Assert.assertFalse(path.equals(otherPath));
        Assert.assertTrue(path.startsWith(beginning));
        Assert.assertFalse(otherPath.startsWith(beginning));
        Assert.assertTrue(path.endsWith(ending));
        Assert.assertFalse(otherPath.endsWith(ending));
    }

    @Test
    public void creatingAPath() {
        Paths.get("/tmp/foo");
        Paths.get(URI.create("file://Users/joe/FileTest.java"));

        FileSystems.getDefault().getPath("/users/sally");
        Paths.get(System.getProperty("user.home") , "logs" , "foo.log");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findRelativePaths() {
        Path p1 = Paths.get("joe");
        Path p2 = Paths.get("sally");
        Assert.assertThat(p1.relativize(p2) ,
            Matchers.anyOf(Matchers.is(Paths.get("../sally")) , Matchers.is(Paths.get("..\\sally"))));
        Assert.assertThat(p2.relativize(p1) ,
            Matchers.anyOf(Matchers.is(Paths.get("../joe")) , Matchers.is(Paths.get("..\\joe"))));

        p1 = Paths.get("home");
        p2 = Paths.get("home/sally/bar");
        Assert.assertThat(p1.relativize(p2) ,
            Matchers.anyOf(Matchers.is(Paths.get("sally/bar")) , Matchers.is(Paths.get("sally\\bar"))));
        Assert.assertThat(p2.relativize(p1) ,
            Matchers.anyOf(Matchers.is(Paths.get("../..")) , Matchers.is(Paths.get("..\\.."))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void joiningPaths() {
        Path path = Paths.get("/home/joe/foo");
        Assert.assertThat(
            path.resolve("bar") ,
            Matchers.anyOf(Matchers.is(Paths.get("/home/joe/foo/bar")) ,
                Matchers.is(Paths.get("\\home\\joe\\foo\\bar"))));

        path = Paths.get("foo").resolve("/home/joe");
        Assert.assertThat(path ,
            Matchers.anyOf(Matchers.is(Paths.get("/home/joe")) , Matchers.is(Paths.get("\\home\\joe"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void PathInfo() throws URISyntaxException {
        Path path = Paths.get("/home/joe/foo");

        Assert.assertThat(path.toString() ,
            Matchers.anyOf(Matchers.is("/home/joe/foo") , Matchers.is("\\home\\joe\\foo")));
        Assert.assertThat(path.getFileName() , Matchers.is(Paths.get("foo")));
        Assert.assertThat(path.getName(0) , Matchers.is(Paths.get("home")));
        Assert.assertThat(path.getNameCount() , Matchers.is(3));
        Assert.assertThat(path.subpath(0 , 2) ,
            Matchers.anyOf(Matchers.is(Paths.get("home/joe")) , Matchers.is("home\\joe")));
        Assert.assertThat(path.getParent() ,
            Matchers.anyOf(Matchers.is(Paths.get("/home/joe")) , Matchers.is(Paths.get("\\home\\joe"))));
        Assert.assertThat(path.getRoot() , Matchers.is(Paths.get("/")));
    }
}
