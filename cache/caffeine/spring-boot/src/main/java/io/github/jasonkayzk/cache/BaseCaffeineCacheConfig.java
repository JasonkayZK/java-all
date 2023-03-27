package io.github.jasonkayzk.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.Data;

@Data
public abstract class BaseCaffeineCacheConfig {

    /**
     * 缓存名称
     */
    private String name = "caffeine";
    /**
     * 默认最大容量，大于0生效
     */
    private int maxSize = 100;
    /**
     * 缓存过期时间（秒），大于0生效
     */
    private int expireDuration = -1;
    /**
     * 缓存刷新时间（秒），大于0生效，且表示这是一个LoadingCache，否则表示是一个普通Cache
     */
    private int refreshDuration = -1;

    private Cache<Object, Object> cache;

    /**
     * 获取特定缓存值
     * @param key key
     * @return 缓存值
     */
    public abstract Object getValue(Object key);

}
