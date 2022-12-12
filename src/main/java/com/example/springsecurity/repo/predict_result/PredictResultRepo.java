package com.example.springsecurity.repo.predict_result;

import com.example.springsecurity.model.predict_result.PredictResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictResultRepo extends JpaRepository<PredictResult, Long> {
    PredictResult findByUsername(String username);
}
