package com.harfield.snail.tool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by harfield on 2016/12/9.
 */
public class TestIterator {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        for(int i=0; i<1000;i++){
            set.add(i+"");
        }
        System.out.println(set.size());
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("10"))
                iterator.remove();
        }
        System.out.println(set.size());
        System.out.println(1+"\001" + "3");
    }
}
