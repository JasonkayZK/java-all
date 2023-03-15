package io.github.jasonkayzk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        try {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                pool.submit(() -> {
                    System.out.printf("%s: %d\n", Thread.currentThread().getName(), finalI);
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}