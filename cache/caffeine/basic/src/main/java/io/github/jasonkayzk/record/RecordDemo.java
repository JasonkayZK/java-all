package io.github.jasonkayzk.record;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class RecordDemo {

    public static void main(String[] args) {
        // 获取统计指标
        Cache<String, String> cache = Caffeine.newBuilder().
                recordStats().build();
        System.out.println(cache.stats());
        System.out.println(cache.estimatedSize());
    }

}
