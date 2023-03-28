package io.github.jasonkayzk.type;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoadingCacheDemo {

    public static void main(String[] args) {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    // 该方法必须实现
                    public String load(@NonNull String k) throws Exception {
                        return "456";
                    }

                    @Override
                    // 如果需要批量加载
                    public @NonNull Map<String, String> loadAll(@NonNull Iterable<? extends String> keys) throws Exception {
                        return new HashMap<String, String>() {
                        };
                    }
                });

        System.out.println(cache.getIfPresent("123")); // null
        System.out.println(cache.get("123"));          // 456
        System.out.println(cache.getAll(Arrays.asList("123", "456")));        // Map<String, String>
    }

}
