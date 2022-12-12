package com.example.springsecurity.controller.nganh_dao_tao;


import com.example.springsecurity.model.nganh_dao_tao.NganhHoc;
import com.example.springsecurity.repo.nganh_dao_tao.NganhHocRepo;
import com.example.springsecurity.service.impl.nganh_hoc.NganhHocServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
public class NganhHocController {
    @Autowired
    NganhHocRepo nganhHocRepo;

    @Autowired
    NganhHocServiceImpl nganhHocService;

    @RequestMapping("/nganhhoc")
    public List<NganhHoc> getAllNganh() {
        return nganhHocRepo.findAll();
    }

    @RequestMapping("/nganhhoc/{id}")
    public NganhHoc getANganh(@PathVariable long id) {
        return nganhHocRepo.findById(id);
    }





    @RequestMapping(method = RequestMethod.POST, value = "/crnh", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<NganhHoc> createNews(NganhHoc newNews) {
        return new ResponseEntity<>(nganhHocService.createNh(newNews), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/upnh/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<NganhHoc> updateNews(@PathVariable Long id, NganhHoc newNews) {
        return new ResponseEntity<>(nganhHocService.updateNh(id, newNews), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delnh/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        return new ResponseEntity<>(nganhHocService.deleteNh(id), HttpStatus.OK);
    }



}
