package com.example.springsecurity.controller.user_grade_point;

import com.example.springsecurity.dto.UserGradePointDto;
import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.user_grade_point.UserGradePoint;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.repo.user_grade_point.UserGradePointRepo;
import com.example.springsecurity.service.impl.rabbitmq.RabbitMQSender;
import com.example.springsecurity.service.impl.user_grade_point.UserGradePointImpl;
import com.example.springsecurity.service.user_grade_point.UserGradePointService;
import com.example.springsecurity.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping("/point")
public class UserGradePointController {
    @Autowired
    UserGradePointRepo userGradePointRepo;

    @Autowired
    UserGradePointImpl userGradePoint;

    @Autowired
    RabbitMQSender rabbitMQSender;


    private final UserRepo userRepo;

    public UserGradePointController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/crpoint", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserGradePoint> createUserPoint(HttpServletRequest request, UserGradePoint newUserGradePoint) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        Long user_id = userRepo.findByUsername(username).getId();
        if (userGradePointRepo.findByUsername(username) == null) {
            UserGradePoint userPoint = userGradePointRepo.save(new UserGradePoint(username, user_id, newUserGradePoint.getMath12(),
                    newUserGradePoint.getPhysic(), newUserGradePoint.getChemistry(), newUserGradePoint.getBiology(),
                    newUserGradePoint.getLiterature(), newUserGradePoint.getNn(), newUserGradePoint.getHistory(), newUserGradePoint.getGeography(),
                    newUserGradePoint.getEthics(), newUserGradePoint.getIt(), newUserGradePoint.getEngineering()));
            rabbitMQSender.send(username);
            return new ResponseEntity<>(userPoint, HttpStatus.CREATED);
        } else {
            throw new ApiRequestException("User Already Post Grade " + username);
        }
    }

    @GetMapping("/getpoint")
    public ResponseEntity<UserGradePoint> getUserPoint(@RequestParam(required = false) String username) {
        UserGradePoint point = userGradePointRepo.findByUsername(username);
        if (point == null) {
            throw new ApiRequestException("Not Found Point With UserName " + username);
        } else {
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }


    @GetMapping("/getpointid")
    public ResponseEntity<UserGradePoint> getUserPointById(@RequestParam(required = false) Long userid) {
        UserGradePoint point = userGradePointRepo.findByUserId(userid);
        if (point == null) {
            throw new ApiRequestException("Not Found Point With UserId " + userid);
        } else {
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }

    @GetMapping("/getpointjwt")
    public ResponseEntity<UserGradePoint> getUserPointByToken(HttpServletRequest request) {
        String userName = Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION));
        UserGradePoint point = userGradePointRepo.findByUsername(userName);
        if (point == null) {
            throw new ApiRequestException("Not Found Point With token " + userName);
        } else {
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }

    @GetMapping("/getavgpoint")
    public ResponseEntity<UserGradePointDto> getAvgPoint(){
//        log.info(userGradePointRepo.findAvg());
        return new ResponseEntity<>(userGradePoint.getAvgPoint(), HttpStatus.OK);
    }

    @PutMapping("/uppoint")
    public ResponseEntity<UserGradePoint> updateUserPoint(HttpServletRequest request, UserGradePoint newUserGradePoint) {
        String userName = Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION));
        UserGradePoint point = userGradePointRepo.findByUsername(userName);
        if (point == null) {
            throw new ApiRequestException("User haven't upload point " + userName);
        } else {
            point.setMath12(newUserGradePoint.getMath12());
            point.setPhysic(newUserGradePoint.getPhysic());
            point.setChemistry(newUserGradePoint.getChemistry());
            point.setBiology(newUserGradePoint.getBiology());
            point.setLiterature(newUserGradePoint.getLiterature());
            point.setNn(newUserGradePoint.getNn());
            point.setHistory(newUserGradePoint.getHistory());
            point.setGeography(newUserGradePoint.getGeography());
            point.setEthics(newUserGradePoint.getEthics());
            point.setIt(newUserGradePoint.getIt());
            point.setEngineering(newUserGradePoint.getEngineering());
            userGradePointRepo.save(point);
            rabbitMQSender.send(userName);
            return new ResponseEntity<>(point, HttpStatus.OK);
        }
    }
}
