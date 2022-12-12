package com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut;

import com.example.springsecurity.model.sex.Sex;
import com.example.springsecurity.repo.sex.SexRepo;
import com.example.springsecurity.service.city_distric_sex_dantoc_kvut.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SexServiceImpl implements SexService {
    @Autowired
    SexRepo sexRepo;
    @Override
    public String getSexById(long id) {
        Sex sex = sexRepo.getSexById(id);
        return sex.getGender();
    }
}
