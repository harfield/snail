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
