package io.github.jasonkayzk.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class MyCacheManager {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        caches.add(new CaffeineCache(cacheName(), generateCache()));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    /**
     * 创建缓存名称
     */
    private String cacheName() {
        return "my-cache";
    }

    /**
     * 创建一个Caffeine缓存
     */
    private Cache<Object, Object> generateCache() {
        return Caffeine.newBuilder().
                maximumSize(100).
                expireAfterWrite(1, TimeUnit.HOURS).
                build();
    }

}
