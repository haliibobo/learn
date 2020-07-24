package com.github.haliibobo.learn.thread.service.framework;

import io.netty.util.concurrent.FastThreadLocalThread;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-14 11:45
 * @description 主逻辑
 * doSomething是在异步线程里，而complete是在main线程里
 */
public class Bootstrap {


    private ExecutorService executorService = Executors.newFixedThreadPool(4);


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        Worker<String,Integer> worker = bootstrap.newWorker();

        Wrapper<String,Integer> wrapper = new Wrapper<>();
        wrapper.setWorker(worker);
        wrapper.setInput("6666");

        bootstrap.doWork(wrapper).addListener(new Listener<Integer>() {
            @Override
            public   void complete(Integer input) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(input);
            }
        });

        System.out.println(Thread.currentThread().getName());

    }
    private <IN,OUT>Wrapper<IN,OUT> doWork(Wrapper<IN,OUT> wrapper) {
        executorService.submit(() -> {
            Worker<IN,OUT> worker = wrapper.getWorker();
            OUT out = worker.action(wrapper.getInput());
            wrapper.getListener().complete(out);
        });

        return wrapper;
    }

    private  Worker<String,Integer> newWorker(){
        return new Worker<String,Integer>() {
            @Override
            public Integer action(String input) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Integer.parseInt(input);
            }
        };
    }


    enum DirectExecutor implements Executor {
        INSTANCE;

        @Override
        public void execute(Runnable command) {
            command.run();
        }

        @Override
        public String toString() {
            return "MoreExecutors.directExecutor()";
        }
    }
}
