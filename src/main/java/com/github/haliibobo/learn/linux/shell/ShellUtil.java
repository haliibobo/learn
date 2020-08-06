package com.github.haliibobo.learn.linux.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-27 17:09
 * @description describe what this class do
 */
public class ShellUtil {



    private final static String[] TEST = {"/bin/sh","-c","cat ~/config/ip_local_port_range | awk '{print$1}'"};

    public static boolean needSocket( int port) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future= executor.submit(() -> {
            InputStream is =null;
            InputStreamReader ir = null;
            BufferedReader br =null;
            ProcessBuilder pb = new ProcessBuilder(TEST);
            pb.redirectErrorStream(true);
            try {
               Process p =Runtime.getRuntime().exec(TEST);
                //Process p = pb.start();
                p.waitFor();
                is =p.getInputStream();
                ir = new InputStreamReader(is);
                br = new BufferedReader(ir);
                String line =br.readLine();
                if (NumberUtils.isDigits(line)) {
                    System.out.println("random port start at " + line);
                    return port >= Integer.parseInt(line);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return true;
            }finally {
                try {
                    if (null != br) {
                        br.close();
                    }
                    if (null != ir) {
                        ir.close();
                    }
                    if (null != is) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        });
        try {
            return future.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
    }

    public static void main(String[] args) {

        boolean result = needSocket(99);
        System.out.println(result);


    }

}
