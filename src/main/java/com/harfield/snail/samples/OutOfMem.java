package com.harfield.snail.samples;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by harfield on 2017/8/22.
 */
public class OutOfMem {
    /* -Xmx = 2g
       -Xms = 2g
       size = 10540000
     */

    String name = null;
    public OutOfMem(Long cnt){
        name = "hello " + cnt + "times ";
    }
    public static void main(String[] args) {
        Map<Long,Object> container = new HashMap<Long,Object>();
        Long idx = 1L;
        while (true){
            container.put(idx++, new OutOfMem(idx));
            if(idx % 10000 == 0){
                System.out.println("map size : " + container.size());
            }
        }

    }
}
