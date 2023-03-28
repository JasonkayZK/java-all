package io.github.jasonkayzk.type;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class AsyncLoadingCacheDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String key = "123";

        AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
                .buildAsync(new AsyncCacheLoader<String, String>() {
                    @Override
                    // 自定义线程池加载
                    public @NonNull CompletableFuture<String> asyncLoad(@NonNull String key, @NonNull Executor executor) {
                        return CompletableFuture.completedFuture("456");
                    }
                });
//                .buildAsync(new CacheLoader<String, String>() {
//                    @Override
//                    // OR，使用默认线程池加载（二者选其一）
//                    public String load(@NonNull String key) throws Exception {
//                        return "456";
//                    }
//                });

        CompletableFuture<String> completableFuture = cache.get(key); // CompletableFuture<String>
        System.out.println(completableFuture.get());; // 阻塞，直至缓存更新完成
    }

}
