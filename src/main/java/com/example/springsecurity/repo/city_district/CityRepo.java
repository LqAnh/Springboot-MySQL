package com.example.springsecurity.repo.city_district;

import com.example.springsecurity.model.city_district.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City,Long> {
    List<City> findCityById(long id);

    City getCityById(long id);
}
