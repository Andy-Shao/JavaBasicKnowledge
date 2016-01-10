package swc.util.function;

import java.util.function.BinaryOperator;

import org.junit.Test;

public class BinaryOperatorTest {

    public static <T> void apply(BinaryOperator<T> action , T one , T two) {
        System.out.println(action.apply(one , two));
    }

    @Test
    public void apply() {
        BinaryOperatorTest.apply((x , y) -> x + y , "Take two T's as input, return one T as output," ,
            "useful for \"reduce\" operations.");
    }
}
