package swc.util.function;

import java.util.function.*;
import org.junit.*;

public class ConsumerTest{

	@Test
	public void accept(){
		accept(x -> { System.out.println(x); }, "Take a T as input, perform some action and don't return.");
		this.accept2(x -> { System.out.println(x); }, "Take a T as input, perform some action and don't return.");
	}

	public static <T> void accept(Consumer<T> action, T t){
		action.accept(t);
	}

	public <T> void accept2(Consumer<T> action, T t){
		action.accept(t);
	}
}
