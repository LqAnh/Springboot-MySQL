package com.example.springsecurity.repo.post_com;

import com.example.springsecurity.model.post_com.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findByPublished(boolean published);

    List<Post> findByTitleContaining(String title);
}