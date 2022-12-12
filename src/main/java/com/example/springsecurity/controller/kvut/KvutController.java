package com.example.springsecurity.controller.kvut;

import com.example.springsecurity.model.kvut.Kvut;
import com.example.springsecurity.repo.kvut.KvutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kvut")
public class KvutController {
    @Autowired
    KvutRepo kvutRepo;

    @GetMapping("/getall")
    public List<Kvut> getAllKvut(){
        return kvutRepo.findAll();
    }

    @GetMapping("/getakvut")
    public List<Kvut> getKvutById(@RequestParam Long kvutid){
        return kvutRepo.findKvutsById(kvutid);
    }


}
