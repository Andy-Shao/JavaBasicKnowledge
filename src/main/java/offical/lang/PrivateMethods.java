package offical.lang;

public interface PrivateMethods {
	private static void myMethod() {
	}
	
	private void myMethodTwo() {
	}
	
	default void methodTest() {
		this.myMethodTwo();
		PrivateMethods.myMethod();
	}
}
