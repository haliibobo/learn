package com.github.haliibobo.learn.java.collection.stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.Stack;

public class ArithExpr {

    /**
     *   add =addLast =offerLast
     *   push =addFirst = offerFirst
     *
     *     不允许为null，初始值最小为8
     * @param args
     */
    public static void main(String[] args) {
        // String expr = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
       // StdOut.print(cal(expr));
        ArrayDeque<Integer> deques = new ArrayDeque<>(2);
        for (int i = 0; i < 12; i++) {
            if(new Random().nextInt(3)>1){
                System.out.print( " tail " + i );
                deques.add(i);
            }else {
                System.out.print(" head " + i);
                deques.push(i);
            }
        }
        System.out.println();
        System.out.println(deques);
    }

    private static double cal( String expr) {
        String[] ss = expr.split(" ");
        ArrayDeque<Double> num = new ArrayDeque<>();
        ArrayDeque<String> ops = new ArrayDeque<>();
        double res = 0;
        for (String c : ss) {
            switch (c){
                case "(":
                    break;
                case ")":
                    res = num.pop();
                    String op  = ops.pop();
                    switch (op){
                        case "+":
                            res = num.pop() + res;
                            break;
                        case "-":
                            res = num.pop() - res;
                            break;
                        case "*":
                            res = num.pop() * res;
                            break;
                        case "/":
                            res = num.pop() / res;
                            break;
                        case "sqrt":
                            res = Math.sqrt(res);
                            break;
                        default:
                    }
                    num.push(res);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "sqrt":
                    ops.push(c);
                    break;
                default:num.push(Double.parseDouble(c));
            }
        }
    return res;
    }
}
