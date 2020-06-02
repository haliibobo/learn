package com.github.haliibobo.learn.retryer;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.AttemptTimeLimiter;
import com.github.rholder.retry.AttemptTimeLimiters;
import com.github.rholder.retry.BlockStrategy;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.StopStrategy;
import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import org.apache.log4j.Logger;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-07-30 18:37
 * @description 重试公共类
 * @url https://blog.csdn.net/oinvuki3375/article/details/79387616
 */
public class RetryerUitl {
    private final static Logger LOGGER = Logger.getLogger(RetryerUitl.class);

    /**
     *
     * @param resultPredicate 结果断言重试
     * @param attemptTimeLimiter 单次任务执行时间限制
     * @param waitStrategy 等待时长
     * @param stopStrategy 停止重试策略
     * @param blockStrategy 任务阻塞策略
     * @param onlyRuntimeException 是否只返回运行错误重试
     * @param listener 监听器
     * @param throwable 特定异常
     * @param <V> 返回值类型
     * @return
     */
   public static <V> Retryer<V> fetchRetryer (
       Predicate<V> resultPredicate, AttemptTimeLimiter<V> attemptTimeLimiter
       , WaitStrategy waitStrategy, StopStrategy stopStrategy
       , BlockStrategy blockStrategy,boolean onlyRuntimeException
       ,RetryListener listener
       ,Class<? extends Throwable> ... throwable){

       RetryerBuilder<V> retryerBuilder = RetryerBuilder.newBuilder();
       //0.配置异常错误重试

       if (throwable == null) {
           if (onlyRuntimeException) {
               //retryIfRuntimeException只会在抛runtime异常的时候才重试，checked异常和error都不重试
               retryerBuilder.retryIfRuntimeException();
           }else {
               //抛出runtime异常、checked异常时都会重试，但是抛出error不会重试
               retryerBuilder.retryIfException();
           }
       } else {
           //指定特定错误
           for (Class<? extends Throwable> exceptionClass : throwable) {
               retryerBuilder.retryIfExceptionOfType(exceptionClass);
           }
       }

       //1.配置结果断言重试
       if(resultPredicate !=null ){
           retryerBuilder.retryIfResult(resultPredicate);
       }

       //2.单次任务执行时间限制（如果单次任务执行超时，则终止执行当前任务）
       if (attemptTimeLimiter != null) {
           retryerBuilder.withAttemptTimeLimiter(attemptTimeLimiter);
       }

       //3.任务阻塞策略
       if(blockStrategy !=null){
           retryerBuilder.withBlockStrategy(blockStrategy);
       }

       //4.等待时长重调策略
       if (waitStrategy != null) {
           retryerBuilder.withWaitStrategy(waitStrategy);
       }

       //5.停止重试策略
       if (stopStrategy != null) {
           retryerBuilder.withStopStrategy(stopStrategy);
       }
       //6.添加监听器
       if(listener !=null) {
           retryerBuilder.withRetryListener(listener);
       }
       return retryerBuilder.build();
   }


    public static Retryer<Boolean> fetchRetryer (int attemptNumber,long sleepTime
        ,long maxEachExecuTime, @Nonnull TimeUnit timeUnit){

        return  fetchRetryer(Predicates.equalTo(false), AttemptTimeLimiters.<Boolean>fixedTimeLimit(maxEachExecuTime, timeUnit)
            , WaitStrategies.fixedWait(sleepTime, timeUnit),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, true,new FeedListener(), (Class<? extends Throwable>[]) null);
    }

    public static Retryer<Boolean> fetchRetryer (int attemptNumber,long sleepTime
        ,long maxEachExecuTime){

        return  fetchRetryer(Predicates.equalTo(false), AttemptTimeLimiters.<Boolean>fixedTimeLimit(maxEachExecuTime, TimeUnit.MILLISECONDS)
            , WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, true,new FeedListener(),(Class<? extends Throwable>[]) null);
    }

    public static Retryer<Boolean> fetchRetryer (int attemptNumber,long sleepTime,@Nonnull TimeUnit timeUnit){

        return  fetchRetryer(Predicates.equalTo(false), null
            , WaitStrategies.fixedWait(sleepTime, timeUnit),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, true,new FeedListener(),(Class<? extends Throwable>[]) null);
    }

    public static Retryer<Boolean> fetchRetryer (int attemptNumber,long sleepTime){

        return  fetchRetryer(Predicates.equalTo(false), null
            , WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, true,new FeedListener(),(Class<? extends Throwable>[]) null);
    }

    public static <V> Retryer<V> fetchCommonRetryer (int attemptNumber,long sleepTime){

        return  fetchRetryer(Predicates.<V>isNull(), null
            , WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, false,new FeedListener(),(Class<? extends Throwable>[]) null);
    }

    public static <V> Retryer<V> fetchCommonRetryer (int attemptNumber,long sleepTime, long duration,
        ExecutorService es){

        return  fetchRetryer(
            Predicates.<V>isNull(), AttemptTimeLimiters.<V>fixedTimeLimit(duration,TimeUnit.MILLISECONDS,es)
            , WaitStrategies.fixedWait(sleepTime, TimeUnit.MILLISECONDS),
            StopStrategies.stopAfterAttempt(attemptNumber)
            , null, false,new FeedListener(),(Class<? extends Throwable>[]) null);
    }

    static class FeedListener implements RetryListener {
        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            // 第几次重试,(注意:第一次重试其实是第一次调用)
            if (attempt.getAttemptNumber() >1){
                LOGGER.warn("retry time:" +(attempt.getAttemptNumber()-1) );
            }
            // 是什么原因导致异常
            if (attempt.hasException()) {
                LOGGER.error("exec " + attempt.getAttemptNumber() + " time",attempt.getExceptionCause());
            }
        }
    }

    public static void main(String[] args) {
        Callable<Boolean> callable = new Callable<Boolean>() {
            public Boolean call() throws Exception {
                Thread.sleep(100000);
                return false; // do something useful here
            }
        };

        try {
            RetryerUitl.fetchRetryer(3,1000,1).call(callable);
            //RetryerUitl.fetchRetryer(3,1000).call(callable);
        } catch (RetryException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
           e.printStackTrace();
        }
    }
}
