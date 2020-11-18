package com.example.springbootrediscache.dtos;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Advert")
@Getter
@Setter
public class AdvertViewDto implements Serializable {
    private String advertId;
    private String views;

    public AdvertViewDto(String advertId, String views) {
        this.advertId = advertId;
        this.views = views;
    }
}
