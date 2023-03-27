package io.github.jasonkayzk.refresh;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class RefreshDemo {

    public static void main(String[] args) {
        LoadingCache<String, String> cache1 = Caffeine.newBuilder().
                refreshAfterWrite(10, TimeUnit.MINUTES).
                build(RefreshDemo::create);
    }

    private static String create(String k) {
        return k;
    }
}
