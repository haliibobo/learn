package com.github.haliibobo.learn.java.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class ThreadSync {
    int [][] data = {{1,2,3},{4,5,6},{7,8,9},{11,12,13},{14,15,16},{17,18,19}};



    /**
     * {@link java.util.concurrent.CyclicBarrier}.
     */
     class Driver {
         void main() throws InterruptedException {
             CountDownLatch startSignal = new CountDownLatch(1);
             CountDownLatch doneSignal = new CountDownLatch(data.length);
             for (int i = 0; i < data.length; ++i) {
                 new Thread(new Worker(startSignal, doneSignal,i)).start();
             }

             doSomethingBefore();
             startSignal.countDown();
             doneSignal.await();
             doSomethingAfter();
         }

         private void doSomethingBefore() {
             System.out.println("resource ready !");
         }
         private void doSomethingAfter() {
             System.out.println("all wokers is done !");
         }
     }


    class Worker implements Runnable {
         private final CountDownLatch startSignal;
         private final CountDownLatch doneSignal;
         private final  int row;
         Worker(CountDownLatch startSignal, CountDownLatch doneSignal,int row) {
             this.startSignal = startSignal;
             this.doneSignal = doneSignal;
             this.row = row;
         }
         public void run() {
             try {
                 startSignal.await();
                 doWork();
                 doneSignal.countDown();
             } catch (InterruptedException ex) {

             }
         }
         void doWork() {
             System.out.println(Thread.currentThread().getName() +",deal done:" +(row+1));
         }
     }

     class Solver {
         final int N;
         final int[][] data;
         final CyclicBarrier barrier;

         class Worker implements Runnable {
             int myRow;
             Worker(int row) {
                 myRow = row;
             }
             public void run() {
                 //while (!done()) {
                     processRow(myRow);
                     try {
                         System.out.println("deal done:" + (myRow+1));
                         barrier.await();
                     } catch (InterruptedException | BrokenBarrierException ex) {
                     }
                 //}
             }
             private void processRow(int myRow) {
                 int sum =0;
                for (int f : data[myRow]){
                    sum +=f;
                }
             }
         }

          Solver(int[][] matrix) {
             data = matrix;
             // row
             N = matrix.length;
             Runnable barrierAction = this::mergeRows;
             barrier = new CyclicBarrier(N, barrierAction);
             List<Thread> threads = new ArrayList<>(N);
             for (int i = 0; i < N; i++) {
                 Thread thread = new Thread(new Worker(i));
                 threads.add(thread);
                 thread.start();
             }
              threads.forEach(thread -> {
                  try {
                      thread.join();
                  } catch (InterruptedException e) {
                  }
              });
         }
         private void mergeRows() {
             for (int i = 0; i < data.length; i++) {
                 int sum =0;
                 for (int j = 0; j < data[i].length; j++) {
                     sum +=data[i][j];
                 }
                 System.out.println(Thread.currentThread().getName() +":" +" ,row:" +(i +1) +",sum:" +sum);
             }
         }
     }

     static class Factory {
          public Factory (int n,int s) throws InterruptedException {
              Semaphore semaphore =new Semaphore(s);
              List<Thread> threads = new ArrayList<>(n);
              for (int i = 0; i<n;i++) {
                  W w = new W(i, semaphore);
                  threads.add(w);
                  w.start();
              }
              threads.forEach(thread -> {
                  try {
                      thread.join();
                  } catch (InterruptedException e) {
                  }
              });
          }
          class W extends Thread{
             private int num;
             private Semaphore semaphore;
             public W(int num,Semaphore semaphore){
                 this.num = num;
                 this.semaphore = semaphore;
             }

             @Override
             public void run() {
                 try {
                     semaphore.acquire();
                     System.out.println("工人"+this.num+"占用一个机器在生产...");
                     Thread.sleep(2000);
                     System.out.println("工人"+this.num+"释放出机器");
                     semaphore.release();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
     }


    @Test
    public void test() throws InterruptedException {
        Driver driver = new Driver();
        driver.main();
    }
    @Test
    public void test2() throws InterruptedException {

        Solver s = new Solver(data);
    }
    @Test
    public void test3() throws InterruptedException {

        Factory s = new Factory(8,5);
    }
}
