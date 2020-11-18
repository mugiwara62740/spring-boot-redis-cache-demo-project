package com.example.springbootrediscache.repositories;

import com.example.springbootrediscache.entities.AdvertView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertViewRepositoryImpl {

    public static final String KEY = "advert";

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(AdvertView advertView) {
        redisTemplate.opsForHash().increment(KEY, advertView.getId(), 1L);
    }
}