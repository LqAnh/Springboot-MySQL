package com.example.springsecurity.service.impl.user_grade_point;

import com.example.springsecurity.dto.UserGradePointDto;
import com.example.springsecurity.repo.user_grade_point.UserGradePointRepo;
import com.example.springsecurity.service.user_grade_point.UserGradePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserGradePointImpl implements UserGradePointService {
    @Autowired
    UserGradePointRepo userGradePointRepo;
    @Override
    public UserGradePointDto getAvgPoint() {
        Float math = userGradePointRepo.findAvgMath();
        Float physic = userGradePointRepo.findAvgPhysic();
        Float chemistry = userGradePointRepo.findAvgChem();
        Float biology = userGradePointRepo.findAvgBio();
        Float literature = userGradePointRepo.findAvgLit();
        Float nn = userGradePointRepo.findAvgNn();
        Float history = userGradePointRepo.findAvgHis();
        Float geography = userGradePointRepo.findAvgGeo();
        Float ethics = userGradePointRepo.findAvgEthic();
        Float it = userGradePointRepo.findAvgIt();
        Float engineering = userGradePointRepo.findAvgEngineer();
        return new UserGradePointDto(math, physic, chemistry, biology, literature, nn, history, geography, ethics, it, engineering);
    }
}
