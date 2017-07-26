package com.harfield.snail.tool;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by harfield on 2017/3/1.
 */
public class SychronizeExp {
    static Object lock = new Object();
    static Random random = new Random();
    ThreadLocal<String> localStr  = new ThreadLocal<String>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Worker("a"));
        service.submit(new Worker("b"));

    }
    public void test(){
        localStr.get();
    }

    static class Worker implements Runnable {
        private String name;

        Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("------------");
                    System.out.println(this.name);
                    try {
                        Thread.sleep(random.nextInt(100000));
                    } catch (InterruptedException e) {
                        System.out.println("exception in run");
                    }
                    System.out.println("-------------");
                }
            }
        }
    }
}
