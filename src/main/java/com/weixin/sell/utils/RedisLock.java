package com.weixin.sell.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public boolean lock(String key,String value){
        //如果成功，获得锁。
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //获得redis中的数据，判断是否锁过期。
        String redisValue = redisTemplate.opsForValue().get(key);
        //如果数据不为空，且数据（上一次的时间）已经过期。
        if(!StringUtils.isEmpty(redisValue) && Long.valueOf(redisValue)<System.currentTimeMillis()){
            //覆盖旧数据并返回旧数据;
            String olderValue = redisTemplate.opsForValue().getAndSet(key, value);
            //解决多个线程同时覆盖的问题
            if(!StringUtils.isEmpty(olderValue)&&olderValue.equals(redisValue)){
                return true;
            }
        }
        return false;
    }

    public void unlock(String key,String value){
        String redisValue = redisTemplate.opsForValue().get(key);
        //判断是否与旧值相等;
        if(!StringUtils.isEmpty(redisValue)){
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

}
