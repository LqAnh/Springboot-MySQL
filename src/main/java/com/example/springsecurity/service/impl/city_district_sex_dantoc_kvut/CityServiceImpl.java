package com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut;

import com.example.springsecurity.model.city_district.City;
import com.example.springsecurity.repo.city_district.CityRepo;
import com.example.springsecurity.service.city_distric_sex_dantoc_kvut.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepo cityRepo;

    @Override
    public String findCityById(long id) {
        City city = cityRepo.getCityById(id);
        return city.getCity();
    }
}
