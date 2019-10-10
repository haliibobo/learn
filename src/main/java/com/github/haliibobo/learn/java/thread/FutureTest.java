package com.github.haliibobo.learn.java.thread;

import java.util.concurrent.*;

public class FutureTest {

  /*  public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                Thread.sleep(1000);
                System.out.println("---------aaaa------------");
                return "aaa";
            }
        });
        executor.execute(future);
        try {
            String result = future.get(500, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
    }*/

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> f = future.thenCombine(CompletableFuture.supplyAsync(() -> 20),(x, y) -> "计算结果："+(x.equals(y)));
        System.out.println(f.get());
    }
}
