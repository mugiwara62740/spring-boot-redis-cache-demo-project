package com.example.springbootrediscache.repository;

import com.example.springbootrediscache.models.AdvertView;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class AdvertViewRepositoryImpl implements AdvertViewRepository {

    public static final String ADVERT_TEST = "ADVERT_1";
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations; //to access redis cache

    public AdvertViewRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public void save(AdvertView advertView) {
            hashOperations.increment(ADVERT_TEST, advertView.getAdvertId(), 1L);
    }

    @Override
    public Map<String, String> findAll() {
        return hashOperations.entries(ADVERT_TEST);
    }

    @Override
    public AdvertView findById(String id) {
        Object advertView = hashOperations.get(ADVERT_TEST,id);
        return Optional.ofNullable(advertView).map(c-> new AdvertView(id,(String) advertView)).orElse(null);
    }

    @Override
    public void delete(String id) {
       hashOperations.delete(ADVERT_TEST, id);
    }
}