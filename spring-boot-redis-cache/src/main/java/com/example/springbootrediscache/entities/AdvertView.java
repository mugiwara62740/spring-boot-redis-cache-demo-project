package com.example.springbootrediscache.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RedisHash("advert")
public class AdvertView implements Serializable {
    @Id
    private String id;
    private String views;

    public AdvertView(String id, String views) {
        this.id = id;
        this.views = views;
    }
}
