package swc.util.stream;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class EffeciencyTest {

    @Ignore
    @Test
    public void forList() {
        List<Integer> numbers = Arrays.asList(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8);
        long runningTime = 0L;
        final int loopTime = 100000;

        runningTime = System.currentTimeMillis();
        for (int i = 0 ; i < loopTime ; i++)
            numbers.stream().filter(number -> number < 10).filter(number -> number > 3)
                .filter(number -> number % 2 == 0).mapToInt(Integer::intValue).sum();
        runningTime = System.currentTimeMillis() - runningTime;
        System.out.println("Stream Running Time Is : " + runningTime + " millis");

        runningTime = System.currentTimeMillis();
        for (int i = 0 ; i < loopTime ; i++)
            for (int number : numbers) {
                @SuppressWarnings("unused")
                int sum = 0;
                if (number < 10 && number > 3 && number % 2 == 0) sum += number;
            }
        runningTime = System.currentTimeMillis() - runningTime;
        System.out.println("Handmade Loop Runnnig Time Is: " + runningTime + " millis");
    }
}
