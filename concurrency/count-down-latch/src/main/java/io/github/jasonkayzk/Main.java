package io.github.jasonkayzk;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int workCnt = 5;
        CountDownLatch countDownLatch = new CountDownLatch(workCnt);
        int[] resArr = new int[workCnt];

        for (int i = 0; i < workCnt; i++) {
            final int finalI = i;
            new Thread(() -> {
                try {
                    int res = 0;
                    for (int x = finalI * 10; x < finalI * 10 + 10; x++) {
                        res += x;
                    }
                    resArr[finalI] = res;
                } finally {
                    System.out.println(Thread.currentThread().getName() + " Task is Done");
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();

        System.out.println("result" + Arrays.toString(resArr));
    }
}