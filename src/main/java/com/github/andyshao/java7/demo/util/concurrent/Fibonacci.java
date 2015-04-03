package com.github.andyshao.java7.demo.util.concurrent;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    private static final long serialVersionUID = 173209688366790822L;
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (this.n <= 10) return this.compute(this.n);
        Fibonacci f1 = new Fibonacci(this.n - 1);
        Fibonacci f2 = new Fibonacci(this.n - 2);
        f1.fork();
        f2.fork();
        return f1.join() + f2.join();
    }

    private int compute(int small) {
        final int[] results = {
            1 , 1 , 2 , 3 , 5 , 8 , 13 , 21 , 34 , 55 , 89
        };
        return results[small];
    }

}
