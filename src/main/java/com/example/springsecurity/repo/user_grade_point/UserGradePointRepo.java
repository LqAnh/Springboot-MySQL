package com.example.springsecurity.repo.user_grade_point;

import com.example.springsecurity.model.user_grade_point.UserGradePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGradePointRepo extends JpaRepository<UserGradePoint, Long> {
    UserGradePoint findByUsername(String username);

    UserGradePoint findByUserId(Long userid);

//    UserGradePoint findByUser_id(Long userId);

    @Query(value = "select AVG(math12)  from user_grade_point", nativeQuery = true)
    Float findAvgMath();

    @Query(value = "select AVG(physic)from user_grade_point", nativeQuery = true)
    Float findAvgPhysic();
    @Query(value = "select AVG(chemistry) from user_grade_point", nativeQuery = true)
    Float findAvgChem();
    @Query(value = "select AVG(biology)  from user_grade_point", nativeQuery = true)
    Float findAvgBio();
    @Query(value = "select  AVG(literature)  from user_grade_point", nativeQuery = true)
    Float findAvgLit();
    @Query(value = "select  AVG(nn) from user_grade_point", nativeQuery = true)
    Float findAvgNn();
    @Query(value = "select  AVG(history) from user_grade_point", nativeQuery = true)
    Float findAvgHis();
    @Query(value = "select AVG(geography)   from user_grade_point", nativeQuery = true)
    Float findAvgGeo();

    @Query(value = "select  AVG(ethics)   from user_grade_point", nativeQuery = true)
    Float findAvgEthic();

    @Query(value = "select  AVG(it)  from user_grade_point", nativeQuery = true)
    Float findAvgIt();

    @Query(value = "select  AVG(engineering)  from user_grade_point", nativeQuery = true)
    Float findAvgEngineer();



}
