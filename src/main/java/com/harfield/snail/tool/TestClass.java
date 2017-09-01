package com.harfield.snail.tool;

/**
 * Created by harfield on 2016/11/28.
 */
public class TestClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        new TestClass();
        Class clazz= TestInner.class;
        Object o = clazz.newInstance();
        System.out.println("abc\r\rabc".replaceAll("\r",""));
        System.out.println("abc.abc".substring("abc.abc".indexOf(".")));
        System.out.println("abc_abc".split("\\s|_")[0]);
        System.out.println(Integer.parseInt("1.2"));
    }
    static class TestInner{
        static {
            System.out.println("in test Inner");
        }
    }
}
