package com.example.springsecurity.service.impl.nganh_hoc;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.news.News;
import com.example.springsecurity.model.nganh_dao_tao.NganhHoc;
import com.example.springsecurity.repo.nganh_dao_tao.NganhHocRepo;
import com.example.springsecurity.service.nganh_hoc.NganhHocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class NganhHocServiceImpl implements NganhHocService {
    @Autowired
    NganhHocRepo nganhHocRepo;

    @Override
    public NganhHoc createNh(NganhHoc news) {
        String title = news.getTabid();
        String des = news.getName();
        String link = news.getLink();
        String point = news.getPoint();
        NganhHoc nh = new NganhHoc(title, des, link,point);
        return nganhHocRepo.save(nh);

    }

    @Override
    public NganhHoc updateNh(long id, NganhHoc news) {
        NganhHoc n = nganhHocRepo.findById(id);
        if (n == null) {
            throw new ApiRequestException("Nganh Hoc not exits " + id);
        } else {
            n.setName(news.getName());
            n.setLink(news.getLink());
            n.setPoint(news.getPoint());
            nganhHocRepo.save(n);
            return n;
        }
    }

    @Override
    public String deleteNh(long id) {
        NganhHoc n = nganhHocRepo.findById(id);
        if (n == null) {
            throw new ApiRequestException("Nganh hoc not exits " + id);
        } else {
            nganhHocRepo.deleteById(id);
        }
        return "nganh hoc deleted";
    }
}
