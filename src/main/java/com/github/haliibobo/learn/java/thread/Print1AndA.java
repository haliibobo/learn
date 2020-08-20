package com.github.haliibobo.learn.java.thread;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print1AndA {


    private static final char[] alphm = new char[26];
    private static final int[] num = new int[26];

    private  static  volatile  boolean printA = false;
    private static  final Lock lock = new  ReentrantLock();

    private static final Object o = new Object();

    private static final Condition alphmLock = lock.newCondition();
    private static final Condition numLock = lock.newCondition();

    public static void main(String[] args) {
        //init
        for (int i =0; i <26;i++){
            alphm[i] = (char) (97 +i);
            num[i] = i+1;
        }
        System.out.println(Arrays.toString(alphm));
        System.out.println(Arrays.toString(num));

        new NumLock3Thread().start();
        new AlphmLock3Thread().start();

    }

    /** **************************volatile************************************ */
    public static class NumThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                if (!printA){
                    System.out.print(num[i++] +" ");
                    printA =true;
                }
            }
        }
    }
    public static class AlphmThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                if (printA){
                    System.out.print(alphm[i++] + " ");
                    printA =false;
                }
            }
        }
    }


    /** **************************ReentrantLock************************************ */


    public static class NumLockThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                lock.lock();
                try {
                    System.out.print(num[i++] + " ");
                    alphmLock.signalAll();
                    numLock.await();

                }catch (Exception e){

                }finally {
                    lock.unlock();
                }
            }
            lock.lock();
            alphmLock.signalAll();
            lock.unlock();
        }
    }

    public static class AlphmLockThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                lock.lock();
                try {
                    System.out.print(alphm[i++] + " ");
                    numLock.signalAll();
                    alphmLock.await();
                }catch (Exception e){
                }finally {
                    lock.unlock();
                }
            }
            lock.lock();
            numLock.signalAll();
            lock.unlock();
        }
    }


    public static class NumLock2Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            lock.lock();
            try {
                while (i < 26){
                    System.out.print(num[i++] + " ");
                    alphmLock.signalAll();
                    numLock.await();
                }
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
            lock.lock();
            alphmLock.signalAll();
            lock.unlock();
        }
    }

    public static class AlphmLock2Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            lock.lock();
            try {
                while (i < 26){
                    System.out.print(alphm[i++] + " ");
                    numLock.signalAll();
                    alphmLock.await();
                }
            }catch (Exception e){
            }finally {
                lock.unlock();
            }
            lock.lock();
            numLock.signalAll();
            lock.unlock();
        }
    }

    public static class NumLock3Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            lock.lock();
            try {
                while (i < 26){
                    System.out.print(num[i++] + " ");
                    numLock.signalAll();
                    numLock.await();
                }
            }catch (Exception e){

            }finally {
                lock.unlock();
            }
            lock.lock();
            numLock.signalAll();
            lock.unlock();
        }
    }

    public static class AlphmLock3Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            lock.lock();
            try {
                while (i < 26){
                    System.out.print(alphm[i++] + " ");
                    numLock.signalAll();
                    numLock.await();
                }
            }catch (Exception e){
            }finally {
                lock.unlock();
            }
            lock.lock();
            numLock.signalAll();
            lock.unlock();
        }
    }

    /** **************************synchronized************************************ */

    public static class NumSyncThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                synchronized (o){
                    System.out.print(num[i++] + " ");
                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
            synchronized (o){
                o.notifyAll();
            }
        }
    }


    public static class AlphmSyncThread extends Thread{
        int i = 0;
        @Override
        public void run() {
            while (i < 26){
                synchronized (o){
                    System.out.print(alphm[i++] + " ");
                    o.notifyAll();;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (o){
                o.notifyAll();
            }
        }
    }

    public static class NumSync2Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            synchronized (o){
                while (i < 26){
                    System.out.print(num[i++] + " ");
                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                    }
                }
                o.notifyAll();
            }
        }
    }


    public static class AlphmSync2Thread extends Thread{
        int i = 0;
        @Override
        public void run() {
            synchronized (o){
                while (i < 26){
                    System.out.print(alphm[i++] + " ");
                    o.notifyAll();;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                    }
                }
                o.notifyAll();
            }
        }
    }
}
