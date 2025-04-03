package exercise.controller;

import exercise.repository.CommentRepository;
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

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post getPost(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody Post newPost){
        postRepository.save(newPost);
    }

    @PutMapping(path = "/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post newPost){
        var currentPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        currentPost.setId(id);
        currentPost.setBody(newPost.getBody());
        currentPost.setTitle(newPost.getTitle());
        postRepository.save(currentPost);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
// END
