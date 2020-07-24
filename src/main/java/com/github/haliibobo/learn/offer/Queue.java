package com.github.haliibobo.learn.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-01-22 16:18
 * @description describe what this class do
 */
public class Queue {
    //["PSH1","PSH2","PSH3","POP","POP","PSH4","POP","PSH5","POP","POP"]
    //private Deque<Integer> stack1 = new ArrayDeque<>();
    private Deque<Integer> stack2 = new ArrayDeque<Integer>();
    private Stack<Integer> stack1 = new Stack<>();
    //private Stack<Integer> stack2 = new Stack<>();

    // 队尾
    public void push(int node) {
        stack1.add(node);
    }
    //队首
    public int pop() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                //TODO 分析
                //stack2.push(stack1.pop());
                stack2.add(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
