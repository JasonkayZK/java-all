package io.github.jasonkayzk.type;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncCacheDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String key = "123";
        AsyncCache<String, String> cache = Caffeine.newBuilder().buildAsync();

        CompletableFuture<String> completableFuture = cache.get(key, k -> "456");
        System.out.println(completableFuture.get()); // 阻塞，直至缓存更新完成
    }

}
