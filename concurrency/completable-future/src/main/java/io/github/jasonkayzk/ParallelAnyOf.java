package io.github.jasonkayzk;

import java.util.concurrent.CompletableFuture;

public class ParallelAnyOf {
    public static void main(String[] args) throws Exception {
        String name = "中国石油";

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.
                supplyAsync(() -> queryCode(name, "https://finance.sina.com.cn/code/"));
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.
                supplyAsync(() -> queryCode(name, "https://money.163.com/code/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);

        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfFetchFromSina = cfQuery.
                thenApplyAsync((code) -> fetchPrice((String) code, "https://finance.sina.com.cn/price/"));
        CompletableFuture<String> cfFetchFrom163 = cfQuery.
                thenApplyAsync((code) -> fetchPrice((String) code, "https://money.163.com/price/"));

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFromSina, cfFetchFrom163);

        // 最终结果:
        cfFetch.thenAccept((result) -> System.out.println("price: " + result));
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
