package com.github.haliibobo.learn.distributed.lock.exception;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-03 11:16
 * @description describe what this class do
 */
public class LockLeaseLostException extends IllegalMonitorStateException {

    /** Serial UID */
    private static final long serialVersionUID = 14567854682567L;

    /** Empty constructor */
    public LockLeaseLostException() {
        super();
    }
}
