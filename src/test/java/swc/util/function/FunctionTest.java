package swc.util.function;

import java.util.function.Function;

import org.junit.Test;

public class FunctionTest {

    public static <T , R> void apply(Function<T , R> action , T t) {
        System.out.println(action.apply(t));
    }

    @Test
    public void apply() {
        FunctionTest.apply(x -> x , "Take a T as input, return an R as ouput.");
    }
}
