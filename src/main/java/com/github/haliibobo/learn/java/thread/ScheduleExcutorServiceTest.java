package com.github.haliibobo.learn.java.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-27 20:56
 * @description describe what this class do
 */
public class ScheduleExcutorServiceTest {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        MyScheduleThread myScheduleThread = new MyScheduleThread("MyScheduleThread");
        MyScheduleThread myScheduleThread2 = new MyScheduleThread("MyScheduleThread2");
        System.out.println("线程：" + Thread.currentThread().getName() + "，执行时间：" + myScheduleThread.paraseDate());
        //scheduledExecutorService.schedule(myScheduleThread, 10, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleAtFixedRate(myScheduleThread,  10, 2, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleAtFixedRate(myScheduleThread, 10, 6, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleWithFixedDelay(myScheduleThread,10, 2, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleWithFixedDelay(myScheduleThread, 2, 1, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleAtFixedRate(myScheduleThread2,  2, 2, TimeUnit.SECONDS);

        scheduledExecutorService.schedule(myScheduleThread, 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(myScheduleThread, 6, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(myScheduleThread, 1, TimeUnit.SECONDS);
    }

    static class MyScheduleThread extends Thread {

        private String threadName;

        public MyScheduleThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String startTime = paraseDate();
            System.out.println("线程：" + threadName + "，执行时间：" + startTime);
        }

        private String paraseDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(new Date());
        }

}
}
