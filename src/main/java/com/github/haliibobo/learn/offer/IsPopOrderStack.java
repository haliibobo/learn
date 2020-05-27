package com.github.haliibobo.learn.offer;

import java.util.Stack;

public class IsPopOrderStack {



    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (popA.length == 0 || popA.length != pushA.length){
            return false;
        }
        int j =0;
        Stack<Integer> stack = new Stack<>();
        for (int value : pushA) {
            stack.push(value);
            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
