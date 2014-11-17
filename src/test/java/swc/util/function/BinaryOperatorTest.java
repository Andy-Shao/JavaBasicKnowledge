package swc.util.function;

import java.util.function.*;
import org.junit.*;

public class BinaryOperatorTest{

	@Test
	public void apply(){
		apply((x,y) -> x+y, "Take two T's as input, return one T as output,", "useful for \"reduce\" operations.");
	}

	public static <T> void apply(BinaryOperator<T> action, T one, T two){
		System.out.println(action.apply(one, two));
	}
}
