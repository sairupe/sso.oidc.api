package com.syriana.sso.oidc.api.utils.redis;

import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Author: wangzhe
 * @Date: 2019-07-09
 */
@Component
public class RedisTplUtils {


    private static RedisTemplate<Object, Object> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        RedisTplUtils.redisTemplate = redisTemplate;
    }

    /**
     * @param function
     * @param <R>
     * @return
     */
    public static <R> R ops(Function<RedisTemplate<Object, Object>, R> function) {
        return function.apply(redisTemplate);
    }

    /**
     * @param <R>
     * @return
     */
    public static <R> R ops(String key) {
        return (R) redisTemplate.opsForValue().get(key);
    }

    /**
     * 是否空值
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return object == null || object instanceof NullValue;
    }

    /**
     * @param <R>
     * @return
     */
    public static <R> R getByKey(String key) {
        return (R) redisTemplate.opsForValue().get(key);
    }

    /**
     * @return
     */
    public static boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * @return
     */
    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @return
     */
    public static void setByKeyExpire(String key, Object object, long expireTime) {
        redisTemplate.opsForValue().set(key, object, expireTime, TimeUnit.SECONDS);
    }

    /**
     * @return
     */
    public static void setByKey(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }
}
