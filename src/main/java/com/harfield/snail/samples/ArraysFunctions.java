package com.harfield.snail.samples;

import java.util.Arrays;
import java.util.List;

/**
 * Created by harfield on 2017/8/16.
 */
public class ArraysFunctions {
    public static void main(String[] args) {
//        ArrayList<String> a = new ArrayList<String>("a");
        /*asList 返回的list 没有 add方法*/
        List<String> list = Arrays.asList("a");
        list.add("b");
    }
}
