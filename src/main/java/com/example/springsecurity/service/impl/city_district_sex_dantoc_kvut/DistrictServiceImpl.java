package com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut;

import com.example.springsecurity.model.city_district.District;
import com.example.springsecurity.repo.city_district.DistrictRepo;
import com.example.springsecurity.service.city_distric_sex_dantoc_kvut.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepo districtRepo;

    @Override
    public String findDistrictById(long id) {
        District dis = districtRepo.getDistrictById(id);
        return dis.getDistrict();
    }
}
