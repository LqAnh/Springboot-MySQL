package com.example.springsecurity.repo.dantoc;

import com.example.springsecurity.model.dantoc.Dantoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DantocRepo extends JpaRepository<Dantoc,Long> {
    List<Dantoc> findAll();

    List<Dantoc> findDantocById(long id);

    Dantoc getDantocById(long id);
}
