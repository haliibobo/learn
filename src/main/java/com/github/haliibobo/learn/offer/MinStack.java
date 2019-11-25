package com.github.haliibobo.learn.offer;

import java.util.Stack;

public class MinStack {

    //定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        if (dataStack.isEmpty() || node < minStack.peek()) {
            minStack.push(node);
        }
        dataStack.push(node);
    }

    public void pop() {
        if (dataStack.peek().intValue() == minStack.peek()) {
            minStack.pop();
        }
        dataStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
