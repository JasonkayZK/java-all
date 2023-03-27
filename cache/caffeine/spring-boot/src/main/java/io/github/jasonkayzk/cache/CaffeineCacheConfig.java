package io.github.jasonkayzk.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    Logger logger = LoggerFactory.getLogger(CaffeineCacheConfig.class);

    @Resource
    private UserCache userCache;

    @Bean
    public CacheManager cacheManager() {
        // 添加所有创建的缓存，这里仅添加一个用于示例
        List<BaseCaffeineCacheConfig> cacheConfigs = new ArrayList<>();
        cacheConfigs.add(userCache);

        // 创建缓存管理器下的所有缓存
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        for (BaseCaffeineCacheConfig config : cacheConfigs) {
            caches.add(new CaffeineCache(config.getName(), generateCache(config)));
            logger.info("registered cache: {}", config.getName());
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    /**
     * 生成cache，并开启统计功能
     *
     * @param config 配置信息
     * @return cache
     */
    private Cache<Object, Object> generateCache(BaseCaffeineCacheConfig config) {
        // 创建缓存
        Cache<Object, Object> cache;
        Caffeine<Object, Object> builder = Caffeine.newBuilder().recordStats();
        if (config.getMaxSize() > 0) {
            builder.maximumSize(config.getMaxSize());
        }
        if (config.getExpireDuration() > 0) {
            builder.expireAfterWrite(config.getExpireDuration(), TimeUnit.SECONDS);
        }
        if (config.getRefreshDuration() > 0) {
            // 创建LoadingCache，需要传入CacheLoader
            builder.refreshAfterWrite(config.getRefreshDuration(), TimeUnit.SECONDS);
            cache = builder.build(cacheLoader(config));
        } else {
            // 创建普通Cache
            cache = builder.build();
        }
        config.setCache(cache);
        return cache;
    }

    /**
     * 构造cache loader
     *
     * @param config 配置
     * @return cache loader
     */
    private CacheLoader<Object, Object> cacheLoader(BaseCaffeineCacheConfig config) {
        // 使用配置类中的getValue()方法
        return config::getValue;
    }

}
