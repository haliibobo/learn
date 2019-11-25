package com.github.haliibobo.learn.offer;

import java.util.Stack;

public class PopOrder {

    /*
     * 输入两个整数序列，第一个序列表示栈的压入顺序。
     * 请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 4,3,5,1,2不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * 用一个容器模仿压入栈，从左到右扫描出栈序列，每次压栈后将满足出栈条件的元素出栈，
     * 最后判断容器是否为空，空说明所有元素都成功出栈，否则有元素未成功出栈，出栈序列错误。
     */

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        //辅助栈
        Stack<Integer> stack = new Stack<>();
        //弹出序列的下标索引
        int popIndex = 0;
        for (int value : pushA) {
            //不停地将pushA中的元素压入栈中，一旦栈顶元素与popA相等了，则开始出栈
            //不相等则继续入栈
            stack.push(value);
            while (!stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        //栈中没有元素了说明元素全部一致，并且符合弹出顺序，那么返回true
        return stack.isEmpty();
    }
}
