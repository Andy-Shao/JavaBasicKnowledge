package swc.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomTest {
    private volatile Random random;

    @Before
    public void before() {
        this.random = new Random(47);
    }

    @Test
    public void testInts() {
        IntStream stream = this.random.ints(13 , 18);
        Assert.assertThat(stream.findFirst().getAsInt() , Matchers.is(16));

        stream = this.random.ints(3);
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        stream.forEach((i) -> {
            int index = atomicInteger.getAndIncrement();
            switch (index) {
            case 0:
                Assert.assertThat(i , Matchers.is(1717241110));
                break;
            case 1:
                Assert.assertThat(i , Matchers.is(-2014573909));
                break;
            case 2:
                Assert.assertThat(i , Matchers.is(229403722));
                break;
            }
        });
    }

    @Test
    public void testLongs() {
        LongStream stream = this.random.longs(123456789L , 987654321L);
        Assert.assertThat(stream.findFirst().getAsLong() , Matchers.is(147089268L));
    }
}
