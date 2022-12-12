package com.example.springsecurity.controller.sex;

import com.example.springsecurity.model.sex.Sex;
import com.example.springsecurity.repo.sex.SexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sex")
public class SexController {
    @Autowired
    SexRepo sexRepo;

    @GetMapping("/getall")
    public List<Sex> getAll(){
        return sexRepo.findAll();
    }

    @GetMapping("/getgender")
    public List<Sex> getGenderById(@RequestParam Long sexid){
        return sexRepo.findAllById(sexid);
    }


}
