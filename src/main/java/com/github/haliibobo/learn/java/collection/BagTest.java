package com.github.haliibobo.learn.java.collection;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * https://docs.oracle.com/javase/8/docs/technotes/guides/language/foreach.html
 */
public class BagTest {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String item = scanner.next();
            bag.add(item);
            if ("q".equalsIgnoreCase(item)){
                break;
            }
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
