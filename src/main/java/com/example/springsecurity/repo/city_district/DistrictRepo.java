package com.example.springsecurity.repo.city_district;

import com.example.springsecurity.model.city_district.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepo extends JpaRepository<District,Long> {

    List<District> findDistrictByCityId(long cityId);

    List<District> findDistrictById(long disId);

    District getDistrictById(long id);
}
