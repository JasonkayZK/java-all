package io.github.jasonkayzk.cache;

import io.github.jasonkayzk.consts.Constants;
import io.github.jasonkayzk.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserCache extends BaseCaffeineCacheConfig {

    /**
     * 缓存名称
     */
    private final String name = "UserCache";

    /**
     * 默认最大容量，大于0生效
     */
    private final int maxSize = 100;

    /**
     * 缓存过期时间（秒），大于0生效
     */
    private final int expireDuration = 86400;

    /**
     * 缓存刷新时间（秒），大于0生效，且表示这是一个LoadingCache，否则表示是一个普通Cache
     */
    private final int refreshDuration = 600;

    /**
     * 获取特定缓存值
     *
     * @param key key
     * @return 缓存值
     */
    public Object getValue(Object key) {
        String[] param = ((String) key).split(Constants.SPLIT_STR);
        return getUser(param[0], param[1]);
    }

    @Cacheable(value = "UserCache",
            key = "#userType + T(io.github.jasonkayzk.consts.Constants).SPLIT_STR + #userId",
            condition = "#userType != 'root'")
    public User getUser(String userType, String userId) {
        if ("1".equals(userId) && "admin".equals(userType)) {
            return new User("1", "admin", "admin-password");
        } else {
            return new User("999", "visitor", "no-password");
        }
    }

    @CacheEvict(value = "UserCache",
            key = "#userType + T(io.github.jasonkayzk.consts.Constants).SPLIT_STR + #userId",
            condition = "#userType != 'root'")
    public void deleteUser(String userType, String userId) {
    }

}
