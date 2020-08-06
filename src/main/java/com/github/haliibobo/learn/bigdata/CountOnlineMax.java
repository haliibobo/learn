package com.github.haliibobo.learn.bigdata;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-06 13:12
 * @description describe what this class do
 */
public class CountOnlineMax {



    @Test
    public void genefile () throws IOException, InterruptedException {
        final CountDownLatch c = new CountDownLatch(100000);

        ExecutorService es = Executors.newFixedThreadPool(128);
        final RandomAccessFile file = new RandomAccessFile("/Users/lizibo/config/login", "rw");
        final FileChannel channel = file.getChannel();
        final FileLock[] lock = {null};

        for (int j =0;j<100;j++){
            es.submit(()->{
                try {
                    while (true) {
                        try {
                            lock[0] = channel.tryLock();

                            if (lock[0] !=null){
                                break;
                            }
                            Thread.sleep(10);
                        }catch (Exception ee){

                        }

                    }

                    file.seek(file.length());
                    for (int i = 0; i < 1000; i++) {
                        int s = new Random().nextInt(86400);
                        int e = s + new Random().nextInt(86400);
                        if (e > 86400) {
                            e = 86400;
                        }
                        file.writeBytes(UUID.randomUUID() + "\t" + s + "\t" + e);
                        file.writeBytes("\r\n");
                        c.countDown();
                    }
                    lock[0].release();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


        c.await();
    }

}
