package com.example.springsecurity.controller;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.news.News;
import com.example.springsecurity.service.news.NewsService;
import com.example.springsecurity.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController()
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }


    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/news/{id}")
    @ResponseBody
    public News getData(HttpServletRequest request, @PathVariable long id) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        log.info(username);
        return newsService.getNewsById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/crnew", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<News> createNews(News newNews) {
        News n = newsService.createNews(newNews);
        return new ResponseEntity<>(n, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/upnew/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<News> updateNews(@PathVariable Long id, News newNews) {
        return new ResponseEntity<>(newsService.updateNews(id, newNews), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delnew/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        return new ResponseEntity<>(newsService.deleteNews(id), HttpStatus.OK);
    }
}
