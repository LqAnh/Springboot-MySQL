package com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut;

import com.example.springsecurity.model.dantoc.Dantoc;
import com.example.springsecurity.repo.dantoc.DantocRepo;
import com.example.springsecurity.service.city_distric_sex_dantoc_kvut.DantocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DantocServiceImpl implements DantocService {
    @Autowired
    DantocRepo dantocRepo;
    @Override
    public String getDantocById(long id) {
        Dantoc dt = dantocRepo.getDantocById(id);
        return dt.getDantoc();
    }
}
