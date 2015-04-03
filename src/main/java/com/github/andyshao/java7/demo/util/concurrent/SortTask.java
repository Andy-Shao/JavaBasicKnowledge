package com.github.andyshao.java7.demo.util.concurrent;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

class SortTask extends RecursiveAction {
    private static final long serialVersionUID = -1010351628295144837L;
    final long[] array;
    final int hi;
    final int lo;
    private int THRESHOLD = 0;//For demo only

    public SortTask(long[] array) {
        this(array , 0 , array.length - 1);
    }

    public SortTask(long[] array , int lo , int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (this.hi - this.lo < this.THRESHOLD) this.sequentiallySort(this.array , this.lo , this.hi);
        else {
            int pivot = this.partition(this.array , this.lo , this.hi);
            System.out.println("\npivot = " + pivot + ", low = " + this.lo + ", high = " + this.hi);
            System.out.println("array" + Arrays.toString(this.array));

            ForkJoinTask.invokeAll(new SortTask(this.array , this.lo , pivot - 1) , new SortTask(this.array ,
                pivot + 1 , this.hi));
        }
    }

    private int partition(long[] array , int lo , int hi) {
        long x = array[hi];
        int i = lo - 1;
        for (int j = lo ; j < hi ; j++)
            if (array[j] <= x) {
                i++;
                this.swap(array , i , j);
            }
        this.swap(array , i + 1 , hi);
        return i + 1;
    }

    private void sequentiallySort(long[] array , int lo , int hi) {
        Arrays.sort(array , lo , hi + 1);
    }

    private void swap(long[] array , int i , int j) {
        if (i != j) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
