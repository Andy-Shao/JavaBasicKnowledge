package functional.interfaces;

import org.junit.Test;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class FInterfaceTest {

    public static void run(FInterface fi) {
        fi.run();
    }

    @Test
    public void action() {
        FInterface fi = () -> {
            System.out.println("FInterface.action()");
        };
        fi.action(x -> {
            x.run();
        });
    }

    @Test
    public void test() {
        FInterfaceTest.run(() -> {
            System.out.println("Lambdas test for FInterface.");
        });
    }
}
