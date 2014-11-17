package swc.util.function;

import java.util.function.*;
import org.junit.*;

public class PredicateTest{

	@Test
	public void test(){
		test(x -> {
			System.out.println(x);
			return true;
		}, "Take a T as input, return a boolean as output.");
	}

	public static <T> void test(Predicate<T> action, T t){
		System.out.println(action.test(t));
	}
}
