package com.example.springsecurity.service.impl.news;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.news.News;
import com.example.springsecurity.repo.news.NewsRepository;
import com.example.springsecurity.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private String pattern = "yyyy-MM-dd";
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return (List<News>) newsRepository.findAll();
    }

    @Override
    public News getNewsById(long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News createNews(News newNews) {
        String title = newNews.getTitle();
        String des = newNews.getDescription();
        String date = new SimpleDateFormat(pattern).format(new Date());
        String link = newNews.getLink();
        News news = new News(title, des, 22, date, link);
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(long id, News newNews) {
        News n = getNewsById(id);
        if (n == null) {
            throw new ApiRequestException("News not exits " + id);
        } else {
            n.setTitle(newNews.getTitle());
            n.setDescription(newNews.getDescription());
            n.setLink(newNews.getLink());
            newsRepository.save(n);
            return n;
        }
    }

    @Override
    public String deleteNews(long id) {
        News n = getNewsById(id);
        if (n == null) {
            throw new ApiRequestException("News not exits " + id);
        } else {
            newsRepository.deleteById(id);
        }
        return "News Deleted";
    }


}
