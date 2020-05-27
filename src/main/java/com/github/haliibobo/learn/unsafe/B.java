package com.github.haliibobo.learn.unsafe;

public class B {
    private long b; // not initialized value
    //private String s;

    public B() {
        this.b = 1; // initialization
        //this.s ="s";
    }

    public long b() {
        return this.b;
    }
        /*private String s(){
            return this.s;
        }*/
}
