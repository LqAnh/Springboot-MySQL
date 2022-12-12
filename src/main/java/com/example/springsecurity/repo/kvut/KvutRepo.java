package com.example.springsecurity.repo.kvut;

import com.example.springsecurity.model.kvut.Kvut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KvutRepo extends JpaRepository<Kvut,Long> {

    List<Kvut> findAll();


    List<Kvut> findKvutsById(long id);

    Kvut getKvutById(long id);
}
