package com.example.springsecurity.controller.city_district;

import com.example.springsecurity.model.city_district.City;
import com.example.springsecurity.repo.city_district.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityRepo cityRepo;

    @GetMapping("/getall")
    public List<City> getAllCity(){
        return cityRepo.findAll();
    }

    @GetMapping("/getacity")
    public List<City> getCityNameById(@RequestParam Long cityid){
        return cityRepo.findCityById(cityid);
    }



}
