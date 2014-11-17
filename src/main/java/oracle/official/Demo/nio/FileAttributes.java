package oracle.official.Demo.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class FileAttributes {

	//FileAttributeView
	BasicFileAttributeView basicFileAttributeView;
	DosFileAttributeView fileAttributeView;
	PosixFileAttributeView posixFileAttributeView;
	FileOwnerAttributeView fileOwnerAttributeView;
	AclFileAttributeView aclFileAttributeView;
	UserDefinedFileAttributeView userDefinedFileAttributeView;
	
	//FileAttribute
	BasicFileAttributes basicFileAttributes;
	DosFileAttributes dosFileAttributes;
	PosixFileAttributes posixFileAttributes;

	public static void main(String[] args) throws URISyntaxException, IOException {
		URL url = Thread.currentThread().getContextClassLoader().getResource("oracle/official/Demo/nio/FileAttributes.class");
		Path file = Paths.get(url.toURI());
		
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
		
		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());
	}
}
