package com.taotao.rest.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/4 15:54
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestRedis {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testConnection() {
        String key = "k1";
        String value = "v1";
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.set(key, value));
        ;
        String getKey = jedis.get(key);
        Assert.assertEquals(value, getKey);
        System.out.println(getKey);

        String key2 = "v2";
        String getKey2 = jedis.get(key2);
        System.out.println(getKey2);

        jedis.close();
        jedisPool.close();
    }
}
