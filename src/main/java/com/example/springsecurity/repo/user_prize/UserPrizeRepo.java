package com.example.springsecurity.repo.user_prize;

import com.example.springsecurity.model.user_prize.UserPrize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPrizeRepo extends JpaRepository<UserPrize, Long> {
    UserPrize findByUsername(String username);

    List<UserPrize> findListByUsername(String username);

    UserPrize findByUserId(Long userid);
}
