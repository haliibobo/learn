package com.github.haliibobo.learn.thread.service.framework;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-07-14 11:41
 * @description 包装器
 */
public class Wrapper<IN,OUT> {

    private IN input;
    private Worker<IN,OUT> worker;
    private Listener<OUT> listener;

    public IN getInput() {
        return input;
    }

    public void setInput(IN input) {
        this.input = input;
    }

    public Worker<IN,OUT> getWorker() {
        return worker;
    }

    public void setWorker(Worker<IN,OUT> worker) {
        this.worker = worker;
    }

    public Listener<OUT> getListener() {
        return listener;
    }

    public void addListener(Listener<OUT> listener) {
        this.listener = listener;
    }

}
