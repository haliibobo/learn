package com.github.haliibobo.learn.offer;

import edu.princeton.cs.algs4.AVLTreeST;
import java.util.Arrays;
import java.util.List;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-11-10 17:37
 * @description describe what this class do
 */
public class ArrayToBST {

    public static void main(String[] args) {
        System.out.println("java.class.path:" + System.getProperty("java.class.path"));
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        AVLTreeST<String,Integer> avlTreeST = new AVLTreeST<>();
        list.forEach(i -> avlTreeST.put(i+"",i));
        System.out.println(avlTreeST.keysInOrder());
        avlTreeST.put("!",2);
     }

}
