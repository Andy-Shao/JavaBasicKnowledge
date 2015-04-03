package oracle.official.Demo.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 
 * Descript:
 * The Files class is the other primary entrypoint of the java.nio.file package.
 * This class offers a rich set of static
 * methods for reading, writing, and manipulating files and directories. The
 * Files methods work on instances of Path
 * objects. Before proceeding to the remaining sections, <br>
 * Copyright: Copyright(c) Aug 28, 2014<br>
 * Encoding:UNIX UTF-8
 *
 */
public class FileOperations {

    private static void delete() throws IOException {
        Path target = Paths.get("bufferedWrite_test.file.target");
        Files.delete(target);
        Files.deleteIfExists(target);
    }

    public static void main(String[] args) throws IOException {
        FileOperations.write();
        FileOperations.move();
        FileOperations.print();
        FileOperations.delete();
    }

    private static void move() throws IOException {
        Path source = Paths.get("bufferedWrite_test.file");
        Path target = Paths.get("bufferedWrite_test.file.target");

        Files.move(source , target , StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.ATOMIC_MOVE);
    }

    private static void print() throws IOException {
        Path target = Paths.get("bufferedWrite_test.file.target");
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(target , charset);) {
            for (String line ; (line = reader.readLine()) != null ;)
                System.out.println(line);
        }
    }

    private static void write() throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        String s = FileOperations.class.getName();
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("bufferedWrite_test.file") , charset)) {
            writer.write(s , 0 , s.length());
        }
    }

}
