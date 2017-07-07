package oracle.offical.demo.util.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
class Fibonacci extends RecursiveTask<Integer> {
    final int n;
    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n <= 1) return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
    
    public static void main(String[] args) {
        int n = 10;
        Fibonacci f = new Fibonacci(n);
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(f);
        System.out.println(result);
    }

}
