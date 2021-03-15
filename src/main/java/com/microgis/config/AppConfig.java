package com.microgis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final CacheManager cacheManager;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("driver", "voyage", "geozone", "device");
    }

    public void evictCache(String name, String key) {
        Cache cache = cacheManager.getCache(name);
        if (cache != null) {
            cache.evict(key);
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}