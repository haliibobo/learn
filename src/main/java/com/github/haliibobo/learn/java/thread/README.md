多线程同步
CountDownLatch(AQS)、CyclicBarrier(ReentrantLock)、Semaphore(AQS)
see ThreadSync.java

1.CountDownLatch相对于CyclicBarrier侧重点是，等待其他线程操作完成后主线程在继续后续的操作
2.CyclicBarrier相对于CountDownLatch侧重点是，所有的线程操作完成后等待一起继续后续操作。
3.CountDownLatch不能重置状态，CyclicBarrier可以重置后多次利用
4.CountDownLatch和CyclicBarrier抛出异常后都需要妥善处理
5.Semaphore于Lock类似，主要用于线程的访问控制，构造时可以指定是否是公平竞争
6.thread.join()主要是让主线程等待子线程执行完毕，有个注意点就是join()执行之前需要获取到子线程的实例对象锁


refer ：https://www.jianshu.com/p/bcddb6400ccb