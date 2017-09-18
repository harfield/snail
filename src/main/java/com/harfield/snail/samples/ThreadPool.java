package com.harfield.snail.samples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Exception in thread pool
 */
public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * solution 2
         */
    /*    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
               throw  new RuntimeException(e);
            }
        });
           ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("throw from runnable");
            }
        });
        executorService.shutdown();
        */

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("throw from runnable");
            }
        });
        executorService.shutdown();
        //submit.get() 抛出执行异常
        submit.get();

    }
}
