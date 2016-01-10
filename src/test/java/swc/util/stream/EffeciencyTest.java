package swc.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class EffeciencyTest {

    @Test
    public void forList() {
        List<Integer> numbers = Arrays.asList(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8);
        final AtomicInteger runTimes = new AtomicInteger(0);

        numbers.stream().filter(i -> {
            runTimes.incrementAndGet();
            return i < 10;
        }).mapToInt(i -> {
            runTimes.incrementAndGet();
            return i.intValue();
        }).sum();
        System.out.println(runTimes.get());

        runTimes.set(0);
        numbers.stream().filter(i -> {
            runTimes.incrementAndGet();
            return i < 10;
        }).filter(i -> {
            runTimes.incrementAndGet();
            return i > 3;
        }).mapToInt(i -> {
            runTimes.incrementAndGet();
            return i.intValue();
        }).sum();
        System.out.println(runTimes.get());

        runTimes.set(0);
        numbers.stream().filter(i -> {
            runTimes.incrementAndGet();
            return i < 10;
        }).filter(i -> {
            runTimes.incrementAndGet();
            return i > 3;
        }).filter(i -> {
            runTimes.incrementAndGet();
            return i % 2 == 0;
        }).mapToInt(i -> {
            runTimes.incrementAndGet();
            return i.intValue();
        }).sum();
        System.out.println(runTimes.get());
    }
}
