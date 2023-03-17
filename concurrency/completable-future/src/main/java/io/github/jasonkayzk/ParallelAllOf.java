package io.github.jasonkayzk;

import java.util.concurrent.CompletableFuture;

public class ParallelAllOf {
    public static void main(String[] args) throws Exception {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> xQueryFromSina = CompletableFuture.
                supplyAsync(() -> queryCode("X", "https://finance.sina.com.cn/code/"));
        CompletableFuture<String> yQueryFrom163 = CompletableFuture.
                supplyAsync(() -> queryCode("Y", "https://money.163.com/code/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture.allOf(xQueryFromSina, yQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> xFetchFromSina = xQueryFromSina.thenApplyAsync((code) -> fetchPrice(code, "https://finance.sina.com.cn/price/"));
        CompletableFuture<String> yFetchFrom163 = yQueryFrom163.thenApplyAsync((code) -> fetchPrice(code, "https://money.163.com/price/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture.allOf(xFetchFromSina, yFetchFrom163);

        // 最终结果:
        xFetchFromSina.thenAccept((result) -> System.out.println("price: " + result));
        yFetchFrom163.thenAccept((result) -> System.out.println("price: " + result));

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException ignored) {
        }
        return name + ":601857";
    }

    static String fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException ignored) {
        }
        return code + " " + 5 + Math.random() * 20 + "$";
    }
}
