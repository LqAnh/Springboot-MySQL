package com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut;

import com.example.springsecurity.model.kvut.Kvut;
import com.example.springsecurity.repo.kvut.KvutRepo;
import com.example.springsecurity.service.city_distric_sex_dantoc_kvut.KvutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KvutServiceImpl implements KvutService {
    @Autowired
    KvutRepo kvutRepo;
    @Override
    public String getKvutById(long id) {
        Kvut kv = kvutRepo.getKvutById(id);
        return kv.getKvut();
    }
}
