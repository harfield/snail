package com.harfield.snail.tool;

/**
 * Created by harfield on 2017/3/13.
 */
public class KafkaConsumer {


    public static void main(String[] args) {

        Double d=200056d;
        String value = (d>10000000?d.doubleValue():d.longValue())+"";

        Number m=d>10000000?d.doubleValue():23;
        System.out.println("m:"+ m);
        System.out.println("value :"+value);
        System.out.println(d.longValue()+"");


        System.out.println(d.doubleValue());

        System.out.println(d+"");
        Number n = 1<2? a():b();

        System.out.println(n);
        System.out.println("a".split("b",2).length);
        System.out.println(String.format("%.6f",0.00005));
    }
    static private int a(){
        System.out.println("a");
        return  10;
    }
    static private double b(){
        System.out.println("b");
        return 22;
    }
}
