package offical.lang;

public class TryWith {

	public void demo() throws Exception{
		MyCloseable one = new MyCloseable();
		MyCloseable two = new MyCloseable();
		try(one; two;) {
			
		}
	}
}
