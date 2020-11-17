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
    public Map<String, String> GetAll(){
       return _advertViewRepository.findAll();
    }

    @GetMapping("/all/{id}")
    public AdvertView GetAll(@PathVariable("id") final Long id){
        return _advertViewRepository.findById(String.valueOf(id));
    }

    @PostMapping("/add")
    public void add(@RequestBody AdvertView advertView){
        _advertViewRepository.save(advertView);
        //return _advertViewRepository.findById(advertView.getAdvertId());
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Long id){
        _advertViewRepository.delete(String.valueOf(id));
    }

}
