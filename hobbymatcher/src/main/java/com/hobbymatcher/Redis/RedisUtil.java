package com.hobbymatcher.Redis;


import com.hobbymatcher.Redis.RedisConfig;
import com.hobbymatcher.util.SerializableUtil;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * Redis工具类
 */

public class RedisUtil {

    private RedisTemplate redisTemplate = new RedisConfig(null).redisTemplate(null);

    private static Jedis jedis = new Jedis();

    public static Object findOneObject(String id) {
        byte[] b = jedis.get(id.getBytes());
        return null;
    }

    public boolean addOneObject(Object object) {
        return false;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

//    public SerializableUtil getSerializableUtil() {
//        return serializableUtil;
//    }
//
//    public void setSerializableUtil(SerializableUtil serializableUtil) {
//        this.serializableUtil = serializableUtil;
//    }
}