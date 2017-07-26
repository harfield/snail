package com.harfield.snail.tool;

/**
 * Created by harfield on 2017/5/24.
 */
public class StringTest {
    static  int i=0;
    public static void main(String[] args) {
        String a = "abc";
        String b = new String("abc");
        System.out.println(a==b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(testTryCatch());
    }

    public static int testTryCatch(){
        try{
            return addi();
        }
        finally {
            return i++;
        }
    }
    public static int addi(){
        return i++;
    }

}
