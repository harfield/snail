package com.harfield.snail.samples;

/**
 * Created by harfield on 2017/8/24.
 */
public class StringFunctions {
    /*
     注意 String 方法中参数的类型
     */
    public static void main(String[] args) {
        for(String s:"a.b.c".split(".")){
            System.out.println(s);
        }
        System.out.println("a.b.c".replace(".",""));
        System.out.println("a.b.c".replace("\\.",""));
        System.out.println("****************");
        for(String s:"a.b.c".split("\\.")){
            System.out.println(s);
        }
    }
}
