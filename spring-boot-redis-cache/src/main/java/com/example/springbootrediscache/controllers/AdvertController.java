package com.example.springbootrediscache.controllers;

import com.example.springbootrediscache.models.AdvertView;
import com.example.springbootrediscache.repository.AdvertViewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/advert")
public class AdvertController {

    private AdvertViewRepository _advertViewRepository;

    public AdvertController(AdvertViewRepository advertViewRepository){
        _advertViewRepository = advertViewRepository;
    }

    @GetMapping("/all")
    public Map<String, AdvertView> GetAll(){
       return _advertViewRepository.findAll();
    }

    @GetMapping("/all/{id}")
    public AdvertView GetAll(@PathVariable("id") final String id){
        return _advertViewRepository.findById(id);
    }

    @PostMapping("/add")
    public AdvertView add(@RequestBody AdvertView advertView){
        _advertViewRepository.save(new AdvertView(advertView.getId(), advertView.getName(),80000L));
        return _advertViewRepository.findById(advertView.getId());

    }


    @PostMapping("/update")
    public AdvertView update(@RequestBody AdvertView advertView){
        _advertViewRepository.update(new AdvertView(advertView.getId(), advertView.getName(),1000L));
        return _advertViewRepository.findById(advertView.getId());

    }


}
