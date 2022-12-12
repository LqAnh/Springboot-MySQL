package com.example.springsecurity.repo.sex;

import com.example.springsecurity.model.sex.Sex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SexRepo extends JpaRepository<Sex,Long> {
    List<Sex> findAll();

    List<Sex> findAllById(Long id);

    Sex getSexById(long id);
}
