package io.github.jasonkayzk;

import java.util.concurrent.CompletableFuture;

public class Async {

    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.
                supplyAsync(Async::fetchPrice);

        // 如果执行成功:
        cf.thenAccept((res) -> System.out.println("price: " + res));

        // 如果执行异常:
        cf.exceptionally((e) -> {
            System.out.println("error: " + e.getMessage());
            return null;
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    private static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

}
