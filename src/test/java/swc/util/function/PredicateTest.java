package swc.util.function;

import java.util.function.Predicate;

import org.junit.Test;

public class PredicateTest {

    public static <T> void test(Predicate<T> action , T t) {
        System.out.println(action.test(t));
    }

    @Test
    public void test() {
        PredicateTest.test(x -> {
            System.out.println(x);
            return true;
        } , "Take a T as input, return a boolean as output.");
    }
}
