package kh.com.foss.sample.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(final CacheProperties cacheProperties) {
        return builder -> {
            RedisCacheConfiguration config = builder.cacheDefaults()
                    .serializeKeysWith(
                            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                            new GenericJackson2JsonRedisSerializer()))
                    .computePrefixWith(cacheName -> {
                        if (Boolean.TRUE.equals(cacheProperties.getRedis().isUseKeyPrefix())) {
                            final var keyPrefix = cacheProperties.getRedis().getKeyPrefix();
                            return String.format("%s%s:", keyPrefix, cacheName);
                        } else {
                            return String.format("%s:", cacheName);
                        }
                    });
            builder.cacheDefaults(config);
        };
    }
}
