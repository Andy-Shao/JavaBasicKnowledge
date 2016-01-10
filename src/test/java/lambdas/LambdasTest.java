package lambdas;

import java.util.ArrayList;
import java.util.Comparator;

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
public class LambdasTest {

    public static <RET> void noParametersWithReturn(NoParametersWithReturn<RET> action) {
        System.out.println("No input and return is " + action.apply());
    }

    public static <IN> void singleParameterWithoutReturn(SingleParameterWithoutReturn<IN> action , IN x) {
        action.apply(x);
    }

    public static <IN , RET> void singleParameterWithReturn(SingleParameterWithReturn<IN , RET> action , IN x) {
        System.out.println("input x=" + x + ", return is " + action.apply(x));
    }

    public static <IN1 , IN2 , RET> void
        twoParametersWithReturn(TwoParametersWithReturn<IN1 , IN2 , RET> action , IN1 x , IN2 y) {
        System.out.println("input x=" + x + ", y=" + y + ", return is " + action.apply(x , y));
    }

    @Test
    public void capturingMethodReference() {
        //x::toString   () -> x.toString
        final Integer x = 89;

        /*
         * NOTE:
         * String str = noParametersWithReturn(x::toString);
         * Could not be successful! Because the Java has a bug about the
         * Genericity.
         */
        LambdasTest.noParametersWithReturn(x::toString);
    }

    @Test
    public void constructorReference() {
        //ArrayList::new    () -> new ArrayList<>()

        /*
         * NOTE:
         * List<?> list = noParametersWithReturn(ArrayList::new);
         * Could not be successful! Because the Java has a bug about the
         * Genericity.
         */
        LambdasTest.noParametersWithReturn(ArrayList::new);
    }

    @Test
    public void createObject() {
        Comparator<String> c = (a , b) -> Integer.compare(a.length() , b.length());
        c.compare("123" , "456");

        Runnable r = () -> {
            System.out.println("Running!");
        };
        new Thread(r).start();
    }

    @Test
    public void nonStaticMethodReference() {
        //Object::toString  x -> x.toString()
        LambdasTest.singleParameterWithReturn(Object::toString , 34.5f);
    }

    @Test
    public void noParametersWithReturn() {
        final int x = 78;
        LambdasTest.noParametersWithReturn(() -> x);
    }

    @Test
    public void singleParameterWithoutReturn() {
        LambdasTest.singleParameterWithoutReturn(x -> {
            System.out.println("input x=" + x);
        } , 45);
    }

    @Test
    public void singleParameterWithReturn() {
        LambdasTest.singleParameterWithReturn(x -> x * x , 15);
    }

    @Test
    public void staticMethodReference() {
        //String::valueOf   x -> String.valueOf(x)

        /*
         * NOTE:
         * Double number = singleParameterWithReturn(String::valueOf, "12.45");
         * Could not be successful! Because the Java has a bug about the
         * Genericity.
         */
        LambdasTest.singleParameterWithReturn(String::valueOf , "12.45");
    }

    @Test
    public void twoParametersWithReturn() {
        LambdasTest.twoParametersWithReturn((Integer x , Integer y) -> {
            return x + y;
        } , 1 , 2);

        LambdasTest.twoParametersWithReturn((x , y) -> x + y , 2 , 3);
    }
}
