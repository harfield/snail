package com.harfield.snail.tool;

/**
 * Created by harfield on 2016/11/28.
 */
public class TestClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        new TestClass();
        Class clazz= TestInner.class;
        Object o = clazz.newInstance();
    }
    static class TestInner{
        static {
            System.out.println("in test Inner");
        }
    }
}
