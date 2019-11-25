package com.github.haliibobo.learn.java.collection.stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class ArithExpr {

    public static void main(String[] args) {
        String expr = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        StdOut.print(cal(expr));
    }

    private static double cal( String expr) {
        String[] ss = expr.split(" ");
        Stack<Double> num = new Stack<>();
        Stack<String> ops = new Stack<>();
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
