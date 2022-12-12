package com.example.springsecurity.controller.user_prize;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.user_prize.UserPrize;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.repo.user_prize.UserPrizeRepo;
import com.example.springsecurity.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Slf4j
@RestController
@RequestMapping("/prize")
public class UserPrizeController {
    @Autowired
    UserPrizeRepo userPrizeRepo;

    private final UserRepo userRepo;

    public UserPrizeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/crprize", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserPrize> createUserPoint(HttpServletRequest request, UserPrize newUserPrize) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        Long user_id = userRepo.findByUsername(username).getId();
        if (userPrizeRepo.findByUsername(username) == null ) {
            UserPrize userPrize = userPrizeRepo.save(new UserPrize(username, newUserPrize.getSubject(), newUserPrize.getLevel(), newUserPrize.getType(), user_id));
            return new ResponseEntity<>(userPrize, HttpStatus.CREATED);
        } else {
            throw new ApiRequestException("Prize Already Posted " + username);
        }
    }

    @GetMapping("/getprize")
    public ResponseEntity<UserPrize> getUserPoint(@RequestParam(required = false) String username) {
        UserPrize prize = userPrizeRepo.findByUsername(username);
        if (prize == null) {
            throw new ApiRequestException("Not Found Prize With UserName " + username);
        } else {
            return new ResponseEntity<>(prize, HttpStatus.OK);
        }
    }


    @GetMapping("/getprizeid")
    public ResponseEntity<UserPrize> getUserPointById(@RequestParam(required = false) Long userid) {
        UserPrize point = userPrizeRepo.findByUserId(userid);
        if (point == null) {
            throw new ApiRequestException("Not Found Prize With UserName " + userid);
        } else {
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }

    @GetMapping("/getprizejwt")
    public ResponseEntity<UserPrize> getUserPointByToken(HttpServletRequest request) {
        String username = Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION));
        UserPrize point = userPrizeRepo.findByUsername(username);
        if (point == null) {
            throw new ApiRequestException("Not Found Prize With UserName " + username);
        } else {
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }



    @RequestMapping(method = RequestMethod.PUT, value = "/upprize", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserPrize> upUserPointByJwt(HttpServletRequest request, UserPrize newUserPrize) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        UserPrize userPrize = userPrizeRepo.findByUsername(username);
        if (userPrize == null) {
            throw new ApiRequestException("User Haven't Post Prize  " + username);
        } else {
            userPrize.setSubject(newUserPrize.getSubject());
            userPrize.setLevel(newUserPrize.getLevel());
            userPrize.setType(newUserPrize.getType());
            userPrizeRepo.save(userPrize);
            return new ResponseEntity<>(userPrize, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteUserPrize(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        UserPrize userPrize = userPrizeRepo.findByUsername(username);
        if (userPrize == null) {
            throw new ApiRequestException("User Prize Not Found " + username);
        } else {
            userPrizeRepo.delete(userPrize);
            return new ResponseEntity<>("user prize deleted", HttpStatus.OK);
        }
    }

}
