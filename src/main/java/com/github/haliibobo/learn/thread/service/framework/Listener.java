package com.github.haliibobo.learn.thread.service.framework;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-14 11:39
 * @description 回调器
 */
public interface Listener<OUT> {
    void complete(OUT out);
}
