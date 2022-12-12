package com.example.springsecurity.controller.dantoc;

import com.example.springsecurity.model.dantoc.Dantoc;
import com.example.springsecurity.repo.dantoc.DantocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dantoc")
public class DantocController {
    @Autowired
    DantocRepo dantocRepo;

    @GetMapping("/getall")
    public List<Dantoc> getAll(){
        return dantocRepo.findAll();
    }

    @GetMapping("/getdantoc")
    public List<Dantoc> getDantocById(@RequestParam Long dantocid){
        return dantocRepo.findDantocById(dantocid);
    }
}
