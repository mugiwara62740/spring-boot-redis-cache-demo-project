package com.example.springbootrediscache.repository;

import com.example.springbootrediscache.models.AdvertView;


import java.util.Map;

public interface AdvertViewRepository {
    void save(AdvertView advertView);

    Map<String, String> findAll();
    AdvertView findById(String id);
    void delete(String id);
}
