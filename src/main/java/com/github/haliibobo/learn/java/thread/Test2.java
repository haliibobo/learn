package com.github.haliibobo.learn.java.thread;

import java.util.Map;
import java.util.concurrent.*;

public class Test2 {


    public static void main(String[] args) throws Exception {
        // 5. submit
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future= executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                get();
                return "success";
            }
        });
        try {
            String result = future.get(10, TimeUnit.HOURS);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
    }

private  static void get () throws Exception{
    Map<String,String> m = null;
    String a = m.get("a");
}
}
