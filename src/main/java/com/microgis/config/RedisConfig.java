package com.microgis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient getClient() {
        return Redisson.create(initConfig());
    }

    private Config initConfig() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec()).useSingleServer()
                .setPassword(password)
                .setAddress("redis://" + host + ":" + port);
        return config;
    }
}