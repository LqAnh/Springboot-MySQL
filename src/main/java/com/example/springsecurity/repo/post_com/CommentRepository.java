package com.example.springsecurity.repo.post_com;

import com.example.springsecurity.model.post_com.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    @Transactional
    void deleteByPostId(long tutorialId);
}