package oracle.offical.demo.util.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@SuppressWarnings("serial")
public class Applyer extends RecursiveAction {
    final double[] array;
    final int lo, hi;
    double result;
    Applyer next; //keeps track of right-hand-side tasks
    public Applyer(double[] array, int lo, int hi, Applyer next) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
        this.next = next;
    }
    
    double atLeaf(int l, int h) {
        double sum = 0;
        for(int i=l; i<h; ++i) //perform leftmost base step
            sum += array[i] * array[i];
        return sum;
    }

    @Override
    protected void compute() {
        int l = lo;
        int h = hi;
        Applyer right = null;
        while(h - l > 1 && getSurplusQueuedTaskCount() <= 3){
            int mid = (l + h) >>> 1;
            right = new Applyer(array , mid , h , right);
            right.fork();
            h = mid;
        }
        double sum = atLeaf(l , h);
        while(right != null) {
            if(right.tryUnfork()) //directly calculate if not stolen
                sum += right.atLeaf(right.lo , right.hi);
            else {
                right.join();
                sum += right.result;
            }
            right = right.next;
        }
        result = sum;
    }

    static final double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array , 0 , n , null);
        long times = System.currentTimeMillis();
        pool.invoke(a);
        times = System.currentTimeMillis() - times;
        System.out.format("Running spend times: %d milliseconds\n" , times);
        return a.result;
    }
    
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        double[] array;
        int arraySize = 50000000;
        array = new Random().doubles().filter(item -> item >= 0)
                .limit(arraySize).toArray();
        sumOfSquares(pool , array);
    }
}
