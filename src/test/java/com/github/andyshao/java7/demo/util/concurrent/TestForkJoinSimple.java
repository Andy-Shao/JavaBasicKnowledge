package com.github.andyshao.java7.demo.util.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestForkJoinSimple {
    private static final int NARRAY = 16; //For demo only
    long[] array = new long[TestForkJoinSimple.NARRAY];
    Random rand = new Random();

    boolean checkSorted(long[] a) {
        for (int i = 0 ; i < a.length - 1 ; i++)
            if (a[i] > a[i + 1]) return false;
        return true;
    }

    @Before
    public void setUp() {
        for (int i = 0 ; i < this.array.length ; i++) {
            this.array[i] = this.rand.nextLong() % 100;//For demo only
            System.out.println("Initial Array: " + Arrays.toString(this.array));
        }
    }

    @Test
    public void testSort() throws Exception {
        ForkJoinTask<Void> sort = new SortTask(this.array);
        ForkJoinPool fjpool = new ForkJoinPool();
        fjpool.submit(sort);
        fjpool.shutdown();

        fjpool.awaitTermination(30 , TimeUnit.SECONDS);

        Assert.assertTrue(this.checkSorted(this.array));
    }
}
