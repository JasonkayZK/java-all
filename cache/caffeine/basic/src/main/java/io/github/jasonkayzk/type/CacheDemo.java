package io.github.jasonkayzk.type;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CacheDemo {

    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder().build();

        System.out.println(cache.getIfPresent("123")); // null
        System.out.println(cache.get("123", k -> "456")); // 456
        System.out.println(cache.getIfPresent("123"));    // 456
        cache.put("123", "789");
        System.out.println(cache.getIfPresent("123"));    // 789
    }

}
