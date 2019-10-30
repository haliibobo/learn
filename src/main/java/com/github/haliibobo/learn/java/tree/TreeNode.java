package com.github.haliibobo.learn.java.tree;

public class TreeNode<T> {
    public TreeNode left;
    public TreeNode right;
    public T value;

    public TreeNode (T value) {
        this.value = value;
    }

}
