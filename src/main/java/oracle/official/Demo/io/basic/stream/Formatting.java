package oracle.official.Demo.io.basic.stream;

/**
 * 
 * Descript:
 * Stream objects that implement formatting are instances of either PrintWriter, a character stream class, or PrintStream, 
 * a byte stream class.
 * <br>
 * --<br>
 * Note:The only PrintStream objects you are likely to need are System.out and System.err. (See I/O from the Command Line 
 * for more on these objects.) When you need to create a formatted output stream, instantiate PrintWriter, not PrintStream.
 * <br>
 * --<br>
 * <ul>
 * <li>print and println format individual values in a standard way.</li>
 * <li>format formats almost any number of values based on a format string, with many options for precise formatting.</li>
 * </ul>
 * <br>
 * Copyright: Copyright(c) Aug 27, 2014<br>
 * Encoding:UNIX UTF-8
 *
 */
public class Formatting {

	public static class Root{
		public static void main(String[] args) {
			int i = 2;
			double r = Math.sqrt(i);
			
			System.out.print("The square root of ");
			System.out.print(i);
			System.out.print(" is ");
			System.out.print(r);
			System.out.println(".");
			
			i =5;
			r = Math.sqrt(i);
			System.out.println("The square root of " + i + " is " + r + ".");
		}
	}
	
	/**
	 * 
	 * Descript:
	 * Like the three used in this example, all format specifiers begin with a % and end with a 1- or 2-character conversion 
	 * that specifies the kind of formatted output being generated. The three conversions used here are:
	 * <ul>
	 * <li>d formats an integer value as a decimal value.</li>
	 * <li>f formats a floating point value as a decimal value.</li>
	 * <li>n outputs a platform-specific line terminator.</li>
	 * </ul>
	 * Here are some other conversions:
	 * <ul>
	 * <li>x formats an integer as a hexadecimal value.</li>
	 * <li>s formats any value as a string.</li>
	 * <li>tB formats an integer as a locale-specific month name.</li>
	 * </ul>
	 * <br>
	 * Copyright: Copyright(c) Aug 27, 2014<br>
	 * Encoding:UNIX UTF-8
	 * @author Andy.Shao
	 *
	 */
	public static class Root2{
		public static void main(String[] args) {
			int i =2;
			double r = Math.sqrt(i);
			
			System.out.format("The square root of %d is %f.%n", i, r);
		}
	}
	
	public static class Format {
	    public static void main(String[] args) {
	        System.out.format("%f, %1$+020.10f %n", Math.PI);
	    }
	}
}
