package com.example.springsecurity.controller.post_com;

import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.post_com.Comment;
import com.example.springsecurity.repo.post_com.CommentRepository;
import com.example.springsecurity.repo.post_com.PostRepository;
import com.example.springsecurity.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/getpostcmt/{postId}/comment")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "postId") Long tutorialId) {
        if (!postRepository.existsById(tutorialId)) {
            throw new ApiRequestException("Not found Tutorial with id = " + tutorialId);
        }

        List<Comment> comments = commentRepository.findByPostId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/getcmt/{id}")
    public ResponseEntity<Comment> getCommentsByCmtId(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Not found Comment with id = " + id));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }




    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/crpostcmt/{postId}/comment")
    @ResponseBody
    public ResponseEntity<Comment> createComment(HttpServletRequest request, @PathVariable(value = "postId") Long postId,
                                                 Comment commentRequest) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String username = Utilities.Util.getUsernameFromJwt(authorizationHeader);
        Comment comment = postRepository.findById(postId).map(tutorial -> {
            commentRequest.setPost(tutorial);
            return commentRepository.save(new Comment(commentRequest.getContent(),username,commentRequest.getPost()));
        }).orElseThrow(() -> new ApiRequestException("Not found Post with id = " + postId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }






//    @PutMapping("/comments/{id}")
//    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ApiRequestException("CommentId " + id + "not found"));
//
//        comment.setContent(commentRequest.getContent());
//
//        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/comments/{id}")
//    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
//        commentRepository.deleteById(id);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/tutorials/{tutorialId}/comments")
//    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
//        if (!postRepository.existsById(tutorialId)) {
//            throw new ApiRequestException("Not found Tutorial with id = " + tutorialId);
//        }
//
//        commentRepository.deleteByPostId(tutorialId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}