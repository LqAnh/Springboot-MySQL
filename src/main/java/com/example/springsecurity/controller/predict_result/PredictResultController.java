package com.example.springsecurity.controller.predict_result;


import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.nganh_dao_tao.NganhHoc;
import com.example.springsecurity.model.predict_result.PredictResult;
import com.example.springsecurity.repo.nganh_dao_tao.NganhHocRepo;
import com.example.springsecurity.repo.predict_result.PredictResultRepo;
import com.example.springsecurity.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController()
@RequestMapping("/predict")
public class PredictResultController {
    @Autowired
    PredictResultRepo predictResultRepo;

    @Autowired
    NganhHocRepo nganhHocRepo;

    @GetMapping("/getprejwt")
    public ResponseEntity<List<Object>> getPredictResultByToken(HttpServletRequest request) {
        String userName = Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION));
        PredictResult predict = predictResultRepo.findByUsername(userName);
        if (predict == null) {
            throw new ApiRequestException("Not Found Predict Result With Token " + userName);
        } else {
            NganhHoc sug1 = nganhHocRepo.findByTabid(predict.getSuggestion1());
            NganhHoc sug2 = nganhHocRepo.findByTabid(predict.getSuggestion2());
            NganhHoc sug3 = nganhHocRepo.findByTabid(predict.getSuggestion3());
            List<Object> respone = new ArrayList<>();
            respone.add(predict);
            respone.add(sug1);
            respone.add(sug2);
            respone.add(sug3);
            return new ResponseEntity<>(respone, HttpStatus.OK);
        }
    }
}
