package com.example.qiwi2022backend.RepositoryImpl;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    private final RedisOperations<String,String> redisOperations;

    public RedisRepository(RedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
    }
    public void save(final String key,final String value){
        redisOperations.opsForValue().set(key,value);
    }
    public String getValue(final String key){
        return redisOperations.opsForValue().get(key);
    }
}
