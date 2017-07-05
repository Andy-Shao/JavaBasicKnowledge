package oracle.offical.demo.util.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.LongStream;

/**
 * 
 * Title: Sample Usages<br>
 * Descript: Here is a simple but complete ForkJoin sort that sorts a given long[] array<br>
 * Copyright: Copryright(c) 5 Jul 2017<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
@SuppressWarnings("serial")
class SortTask extends RecursiveAction{
    static final int THRESHOLD = 1000;
    final long[] array; final int lo, hi;
    
    SortTask(long[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }
    
   SortTask(long[] array) { this(array, 0, array.length); }

    @Override
    protected void compute() {
        if(hi - lo < THRESHOLD) sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }

    private void merge(int lo2 , int mid , int hi2) {
        long[] buf = Arrays.copyOfRange(array , lo , mid);
        for(int i=0, j=lo, k=mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[i++];
    }

    private void sortSequentially(int lo2 , int hi2) {
        Arrays.sort(array, lo, hi);
    }

    public static void main(String[] args) {
        int arraySize = 50000000;
        LongStream longStream = new Random().longs();
        long[] array = longStream.filter(item -> item >=0)
            .limit(arraySize).toArray();
        
        SortTask task = new SortTask(array);
        ForkJoinPool pool = new ForkJoinPool();
        long times = System.currentTimeMillis();
        pool.invoke(task);
        times = System.currentTimeMillis() - times;
        System.out.format("Running spend times: %d milliseconds\n" , times);
        pool.shutdown();
        
        LongStream longStream2 = new Random().longs();
        array = longStream2.filter(item -> item >=0)
                .limit(arraySize).toArray();
        long times2 = System.currentTimeMillis();
        Arrays.sort(array);
        times2 = System.currentTimeMillis() - times2;
        System.out.format("Running spend times: %d milliseconds\n" , times2);
        
    }
}
