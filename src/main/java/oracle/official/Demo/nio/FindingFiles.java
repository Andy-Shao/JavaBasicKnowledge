package oracle.official.Demo.nio;

import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class FindingFiles {

    public static void main(String[] args) throws URISyntaxException {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.class");

        Path filename = Paths.get("FindingFiles.java");
        if (matcher.matches(filename)) System.out.println(filename);

        System.out.println("END");
    }
}
