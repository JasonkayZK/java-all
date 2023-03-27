package io.github.jasonkayzk.dao;

import io.github.jasonkayzk.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

public class UserDao {

    @Caching(cacheable = @Cacheable("users"),
            evict = {@CacheEvict("cache2"),
                    @CacheEvict(value = "cache3", allEntries = true)})
    public User find(Integer id) {
        return null;
    }

}
