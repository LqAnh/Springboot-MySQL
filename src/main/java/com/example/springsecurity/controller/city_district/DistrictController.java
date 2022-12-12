package com.example.springsecurity.controller.city_district;

import com.example.springsecurity.model.city_district.District;
import com.example.springsecurity.repo.city_district.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictRepo districtRepo;

    @GetMapping("/getallDis")
    public List<District> getDistrict(){
        return districtRepo.findAll();
    }

    @GetMapping("/getall")
    public List<District> getDistrictByCityId(@RequestParam Long cityid){
        return districtRepo.findDistrictByCityId(cityid);
    }

    @GetMapping("/getadis")
    public List<District> getDistrictById(@RequestParam Long disid){
        return districtRepo.findDistrictById(disid);
    }

}
