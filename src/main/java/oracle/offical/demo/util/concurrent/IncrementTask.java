package oracle.offical.demo.util.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class IncrementTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    final long[] array; final int lo, hi;
    IncrementTask(long[] array, int lo, int hi) {
        this.array = array; 
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if(hi - lo < THRESHOLD){
            for(int i=lo; i< hi; ++i) 
                array[i]++;
        } else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new IncrementTask(array , lo , mid), new IncrementTask(array , mid , hi));
        }
    }

    public static void main(String[] args) {
        int arrySize = 50000000;
        long[] array;
        Random random = new Random();
        array = random.longs().filter(item -> item >= 0)
                .limit(arrySize).toArray();
        
        IncrementTask task = new IncrementTask(array , 0 , array.length);
        ForkJoinPool pool = new ForkJoinPool();
        long times = System.currentTimeMillis();
        pool.invoke(task);
        times = System.currentTimeMillis() - times;
        System.out.format("Running spend times: %d milliseconds\n" , times);
        
//        long times2 = System.currentTimeMillis();
//        for(int i =0; i< array.length; i++)
//            array[i]++;
//        times2 = System.currentTimeMillis() - times2;
//        System.out.format("Running spend times: %d milliseconds\n" , times2);
    }
}
