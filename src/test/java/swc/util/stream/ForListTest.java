package swc.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForListTest {
    private volatile List<Integer> integers;

    @Before
    public void before() {
        this.integers = Arrays.asList(1 , 2 , 3 , 4 , 5 , 6 , 5 , 4 , 3 , 2 , 1);
    }

    @Test
    public void reduceTest() {
        Optional<Integer> max = this.integers.stream().reduce(Integer::max);
        Assert.assertThat(max.get() , Matchers.is(Integer.valueOf(6)));

        int maxInt = this.integers.stream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE , Integer::max);
        Assert.assertThat(maxInt , Matchers.is(6));
    }
}
