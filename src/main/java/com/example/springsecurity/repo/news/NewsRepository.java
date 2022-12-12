package com.example.springsecurity.repo.news;

import com.example.springsecurity.model.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    //News findByEmail(String email);


    @Override
    List<News> findAll();

    News findById(long id);


}
