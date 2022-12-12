package com.example.springsecurity.service.user_grade_point;

import com.example.springsecurity.dto.UserGradePointDto;
import com.example.springsecurity.model.user_grade_point.UserGradePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGradePointService  {
    UserGradePointDto getAvgPoint();
}
