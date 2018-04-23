package com.taotao.order.dao;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/4 16:02
 * @Description:
 */
public interface RedisDao {

    String set(String key, String value);

    String get(String key);

    Long del(String key);

    /**
     * 设置hash类型的值
     */
    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String field);

    /**
     * 对应的数字加1
     */
    Long incr(String key);

    /**
     * 字段过期时间(秒为单位)
     */
    Long ttl(String key);

    /**
     * 设置过期时间
     */
    Long expire(String key, Integer second);
}
