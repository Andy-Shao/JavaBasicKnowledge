package swc.util.function;

import java.util.function.Supplier;

import org.junit.Test;

public class SupplierTest {

    public static <T> void get(Supplier<T> action) {
        System.out.println(action.get());
    }

    @Test
    public void get() {
        SupplierTest.get(() -> "With nothing as input, return a T.");
    }
}
