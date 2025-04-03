package exercise.controller;

import exercise.model.Post;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Comment getComment(@PathVariable Long id){
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody Comment newComment){
        commentRepository.save(newComment);
    }

    @PutMapping(path = "/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody Comment newComment){
        var currentComment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        currentComment.setId(id);
        currentComment.setBody(newComment.getBody());
        commentRepository.save(currentComment);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
    }
}
// END
