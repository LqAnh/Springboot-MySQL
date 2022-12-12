package com.example.springsecurity.controller.post_com;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.post_com.Post;
import com.example.springsecurity.repo.post_com.PostRepository;
import com.example.springsecurity.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/getposts")
    public ResponseEntity<List<Post>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Post> tutorials = new ArrayList<Post>();

        if (title == null)
            postRepository.findAll().forEach(tutorials::add);
        else
            postRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/getpost/{id}")
    public ResponseEntity<Post> getTutorialById(@PathVariable("id") long id) {
        Post tutorial = postRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }



    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/crpost")
    @ResponseBody
    public ResponseEntity<Post> createTutorial(HttpServletRequest request, Post tutorial) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        Post _tutorial = postRepository.save(new Post(tutorial.getTitle(), tutorial.getDescription(), username));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }


//    @PutMapping("/tutorials/{id}")
//    public ResponseEntity<Post> updateTutorial(@PathVariable("id") long id, @RequestBody Post tutorial) {
//        Post _tutorial = postRepository.findById(id)
//                .orElseThrow(() -> new ApiRequestException("Not found Tutorial with id = " + id));
//
//        _tutorial.setTitle(tutorial.getTitle());
//        _tutorial.setDescription(tutorial.getDescription());
//        _tutorial.setPublished(tutorial.isPublished());
//
//        return new ResponseEntity<>(postRepository.save(_tutorial), HttpStatus.OK);
//    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        postRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        postRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/tutorials/published")
//    public ResponseEntity<List<Post>> findByPublished() {
//        List<Post> tutorials = postRepository.findByPublished(true);
//
//        if (tutorials.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(tutorials, HttpStatus.OK);
//    }
}