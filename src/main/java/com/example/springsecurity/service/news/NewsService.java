package com.example.springsecurity.service.news;



import com.example.springsecurity.model.news.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(long id);

    News createNews(News news);

    News updateNews(long id, News news);

    String deleteNews(long id);




}
