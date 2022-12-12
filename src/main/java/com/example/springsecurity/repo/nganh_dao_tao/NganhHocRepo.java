package com.example.springsecurity.repo.nganh_dao_tao;

import com.example.springsecurity.model.nganh_dao_tao.NganhHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NganhHocRepo extends JpaRepository<NganhHoc, Long> {
    @Override
    List<NganhHoc> findAll();

    NganhHoc findById(long id);

    NganhHoc deleteById(long id);

    NganhHoc findByTabid(String id);



}
