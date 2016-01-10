package collections;

import java.util.Arrays;
import java.util.Spliterator;

import org.junit.Ignore;
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
public class SpliteratorTest {
    private volatile Spliterator<String> spliterator;

    @Test
    public void before() {
        this.spliterator = Arrays.asList("one" , "two" , "three").spliterator();
    }

    @Ignore
    @Test
    public void forEachRemaining() {
        this.spliterator.forEachRemaining(t -> {
            System.out.println(t);
        });
    }

    public void getComparator() {
        this.spliterator.getComparator();
    }
}
