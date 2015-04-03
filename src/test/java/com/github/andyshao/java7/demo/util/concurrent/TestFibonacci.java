package com.github.andyshao.java7.demo.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestFibonacci {

    @Test
    public void test() throws InterruptedException , ExecutionException {
        ForkJoinTask<Integer> fjt = new Fibonacci(45);
        ForkJoinPool fjpool = new ForkJoinPool();
        Future<Integer> result = fjpool.submit(fjt);

        //do something
        System.out.println(result.get());
    }
}
