package swc.util.function;

import java.util.function.*;
import org.junit.*;

public class SupplierTest{

	@Test
	public void get(){
		get(() -> "With nothing as input, return a T.");	
	}

	public static <T> void get(Supplier<T> action){
		System.out.println(action.get());
	}
}
