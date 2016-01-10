package swc.util.function;

import java.util.function.Consumer;

import org.junit.Test;

public class ConsumerTest {

    public static <T> void accept(Consumer<T> action , T t) {
        action.accept(t);
    }

    @Test
    public void accept() {
        ConsumerTest.accept(x -> {
            System.out.println(x);
        } , "Take a T as input, perform some action and don't return.");
        this.accept2(x -> {
            System.out.println(x);
        } , "Take a T as input, perform some action and don't return.");
    }

    public <T> void accept2(Consumer<T> action , T t) {
        action.accept(t);
    }
}
