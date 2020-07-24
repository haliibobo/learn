package com.github.haliibobo.learn.thread.service.framework;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-14 11:36
 * 异步工作线程
 */
public interface Worker<IN,OUT> {
    OUT action(IN  in);

    /**
     * 超时、异常时，返回的默认值
     *
     * @return 默认值
     */
    default OUT defaultValue() {
        return null;
    }
}
