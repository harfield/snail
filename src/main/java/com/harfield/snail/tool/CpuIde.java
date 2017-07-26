package com.harfield.snail.tool;

/**
 * Created by harfield on 2017/6/2.
 */
public class CpuIde {
    static long i=1;
    public static void main(String[] args) throws InterruptedException {
        while(true){
            Double sin = Math.sin(i / 60000.0 *Math.PI*2);
            Thread.currentThread().sleep(sin.longValue() + 1);
            i++;
        }
    }
}
