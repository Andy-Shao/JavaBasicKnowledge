package oracle.official.Demo.io.basic.stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 
 * Descript:
 * Objects of type Scanner are useful for breaking down formatted input into tokens and translating individual tokens 
 * according to their data type.
 * <br>
 * Copyright: Copyright(c) Aug 27, 2014<br>
 * Encoding:UNIX UTF-8
 *
 */
public class Scanning {
	
	public static class ScanXan{
		
		public static void main(String[] args) throws IOException {
			try(
					InputStream inputStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("oracle/official/Demo/io/basic/stream/xanadu.txt");
					Scanner s = new Scanner(inputStream);
					){
				while(s.hasNext()) System.out.println(s.next());
			}
		}
	}

	public static class ScanSum{
		public static void main(String[] args) throws IOException {
			double sum = 0;
			try(
					InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("oracle/official/Demo/io/basic/stream/usnumbers.txt");
					Scanner s = new Scanner(inputStream);
			){
				while(s.hasNext()){
					if(s.hasNextDouble()) sum += s.nextDouble();
					else s.next();
				}
			}
			System.out.println(sum);
		}
	}
}
