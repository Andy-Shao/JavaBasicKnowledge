package lambdas;
import java.util.*;
import org.junit.*;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 14, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class LambdasTest{
    
    @Test
    public void twoParametersWithReturn(){
        LambdasTest.twoParametersWithReturn((Integer x, Integer y) -> { return x+y; }, 1, 2);

        LambdasTest.twoParametersWithReturn((x, y) -> x+y, 2, 3);
    }

    public static <IN1, IN2, RET> void twoParametersWithReturn(TwoParametersWithReturn<IN1, IN2, RET> action, IN1 x, IN2 y){
        System.out.println("input x=" + x + ", y=" + y + ", return is " + action.apply(x, y));
    }

    @Test
    public void singleParameterWithReturn(){
        LambdasTest.singleParameterWithReturn(x -> x * x, 15);
    }

    public static <IN, RET> void singleParameterWithReturn(SingleParameterWithReturn<IN, RET> action, IN x){
        System.out.println("input x=" + x + ", return is " + action.apply(x));
    }

    @Test
    public void noParametersWithReturn(){
        final int x = 78;
        LambdasTest.noParametersWithReturn(() -> x);
    }

    public static <RET> void noParametersWithReturn(NoParametersWithReturn<RET> action){
        System.out.println("No input and return is " + action.apply());
    }

    @Test
    public void singleParameterWithoutReturn(){
        LambdasTest.singleParameterWithoutReturn(x -> { System.out.println("input x=" + x); }, 45);
    }

    public static <IN> void singleParameterWithoutReturn(SingleParameterWithoutReturn<IN> action, IN x){
        action.apply(x);
    }

    @Test
    public void staticMethodReference(){
        //String::valueOf   x -> String.valueOf(x)

        /* NOTE:
         * Double number = singleParameterWithReturn(String::valueOf, "12.45");
         * Could not be successful! Because the Java has a bug about the Genericity.
         */
        singleParameterWithReturn(String::valueOf, "12.45");
    }

    @Test
    public void nonStaticMethodReference(){
        //Object::toString  x -> x.toString()
        singleParameterWithReturn(Object::toString, 34.5f);
    }

    @Test
    public void capturingMethodReference(){
        //x::toString   () -> x.toString
        final Integer x = 89;

        /* NOTE:
         * String str = noParametersWithReturn(x::toString);
         * Could not be successful! Because the Java has a bug about the Genericity.
         */
        noParametersWithReturn(x::toString);
    }

    @Test
    public void constructorReference(){
        //ArrayList::new    () -> new ArrayList<>()

        /* NOTE:
         * List<?> list = noParametersWithReturn(ArrayList::new);
         * Could not be successful! Because the Java has a bug about the Genericity.
         */
        noParametersWithReturn(ArrayList::new);
    }

    @Test
    public void createObject(){
        Comparator<String> c = (a, b) -> Integer.compare(a.length(), b.length());
        c.compare("123", "456");

        Runnable r = () -> {System.out.println("Running!");};
        new Thread(r).start();
    }
}
