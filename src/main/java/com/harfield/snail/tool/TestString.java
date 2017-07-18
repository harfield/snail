package com.harfield.snail.tool;

/**
 * Created by harfield on 2017/1/10.
 */
public class TestString {

    public   String a="aa";
    void test(){
        final String c="cc";
        String b="bb";
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }

    public static void main(String[] args) {

        String t1="abc";
        String t2="abc";
        String t3=new String("abc");
        String t4=new String("abc");
        System.out.println(t1==t2); //true
        System.out.println(t1==t3);//false
        System.out.println(t3==t4);//false

        System.out.println("aaaa".split("a",-1).length);

        System.out.println(("20015" + "\001" + 1 + "\001" + "\001" ).split("\001").length);
        Integer b=null;
        System.out.println(b == (Integer) 123);
        System.out.println((Integer)123 == b);
    }



}
