package io.github.jasonkayzk;

import java.util.concurrent.CompletableFuture;

public class Serial {


    public static void main(String[] args) throws Exception {

        String name = "中国石油";

        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.
                supplyAsync(() -> queryCode(name));
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<String> cfFetch = cfQuery.
                thenApplyAsync(Serial::fetchPrice);

        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return name + ":601857";
    }

    static String fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        return code + " " + (5 + Math.random() * 20) + "$";
    }

}
