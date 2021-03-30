package com.microgis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@Configuration
public class AppConfig {

    private CacheManager cacheManager;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("device");
    }

    public void evictCache(String name, String key) {
        Cache cache = cacheManager.getCache(name);
        if (cache != null) {
            cache.evict(key);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

}