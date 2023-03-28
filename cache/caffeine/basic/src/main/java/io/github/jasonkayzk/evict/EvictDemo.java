package io.github.jasonkayzk.evict;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class EvictDemo {

    public static void main(String[] args) {
        // 创建一个最大容量为10的缓存
        Cache<String, String> cache1 = Caffeine.newBuilder().
                maximumSize(10).build();

        // 创建一个写入5s后过期的缓存
        Cache<String, String> cache2 = Caffeine.newBuilder().
                expireAfterWrite(5, TimeUnit.SECONDS).build();

        // 创建一个访问1s后过期的缓存
        Cache<String, String> cache3 = Caffeine.newBuilder().
                expireAfterAccess(1, TimeUnit.SECONDS).build();
    }

}
