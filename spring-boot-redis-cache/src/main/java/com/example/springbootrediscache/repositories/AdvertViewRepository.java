package com.example.springbootrediscache.repositories;

import com.example.springbootrediscache.entities.AdvertView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertViewRepository extends CrudRepository<AdvertView, String> {}
