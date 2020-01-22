package com.github.haliibobo.learn.offer;

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
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    // 队尾
    public void push1(int node) {
        stack1.add(0,node);
    }
    //队首
    public int pop1() {
       return stack1.pop();
    }

    // 队尾
    public void push2(int node) {
        stack1.add(node);
    }
    //队首
    public int pop2() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
