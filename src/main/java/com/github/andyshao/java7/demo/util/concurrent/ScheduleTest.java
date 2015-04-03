package com.github.andyshao.java7.demo.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Title:<br>
 * Descript: ScheduledThreadPool执行延时任务
 * 多线程执行多延时任务
 * 单线程执行多延时任务 <br>
 * Copyright: Copryright(c) Apr 3, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ScheduleTest {
    static class MySchecudledTask implements Runnable {
        private String tname;

        public MySchecudledTask(String tname) {
            this.tname = tname;
        }

        @Override
        public void run() {
            System.out.println(this.tname + " task is begining");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.tname + " task is finished");
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService singleThread = Executors.newSingleThreadScheduledExecutor();

        threadPool.schedule(new MySchecudledTask("t1") , 1 , TimeUnit.SECONDS);
        threadPool.schedule(new MySchecudledTask("t2") , 1 , TimeUnit.SECONDS);

        singleThread.schedule(new MySchecudledTask("t3") , 1 , TimeUnit.SECONDS);
        singleThread.schedule(new MySchecudledTask("t4") , 1 , TimeUnit.SECONDS);

        threadPool.shutdown();
        singleThread.shutdown();
    }
}
