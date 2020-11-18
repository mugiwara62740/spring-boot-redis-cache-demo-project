package com.example.springbootrediscache.controllers;

import com.example.springbootrediscache.dtos.AdvertViewDto;
import com.example.springbootrediscache.entities.AdvertView;
import com.example.springbootrediscache.repositories.AdvertViewRepository;
import com.example.springbootrediscache.repositories.AdvertViewRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/advert")
public class AdvertController {
    @Autowired
    private AdvertViewRepository advertViewRepository;

    @Autowired
    private AdvertViewRepositoryImpl advertViewRepositoryImpl;

    @GetMapping("/all")
    public List<AdvertViewDto> GetAll(){
        List<AdvertViewDto> advertViews = new ArrayList<>();
        advertViewRepository.findAll().forEach(advertView -> advertViews.add(new AdvertViewDto(advertView.getId(), advertView.getViews())));
        return advertViews;
    }

    @GetMapping("/all/{id}")
    public AdvertViewDto GetAll(@PathVariable("id") final Long id){
        AdvertView advertView = advertViewRepository.findById(String.valueOf(id)).get();
        return new AdvertViewDto(advertView.getId(), advertView.getViews());
    }

    @PostMapping("/add")
    public void add(@RequestBody AdvertView advertView){
        advertViewRepositoryImpl.save(advertView);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Long id){
        advertViewRepository.deleteById(String.valueOf(id));
    }

}
