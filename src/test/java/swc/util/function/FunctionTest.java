package swc.util.function;

import org.junit.*;
import java.util.function.*;

public class FunctionTest{

	@Test
	public void apply(){
		apply(x -> x, "Take a T as input, return an R as ouput.");
	}	

	public static <T,R> void apply(Function<T,R> action, T t){
		System.out.println(action.apply(t));
	}
}
