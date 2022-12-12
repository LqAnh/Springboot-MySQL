package com.example.springsecurity.service.nganh_hoc;

import com.example.springsecurity.model.news.News;
import com.example.springsecurity.model.nganh_dao_tao.NganhHoc;

import java.util.List;

public interface NganhHocService {

    NganhHoc createNh(NganhHoc news);

    NganhHoc updateNh(long id, NganhHoc nh);

    String deleteNh(long id);
}
