package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "")
    public List<PostDTO> index(){
        var post = postRepository.findAll();
        var result = post.stream()
                .map(this::toPostDTO)
                .toList();
        return result;

    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id){
        return toPostDTO(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found")));
    }

    private PostDTO toPostDTO(Post post){
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());

        var comments = commentRepository.findByPostId(dto.getId());
        dto.setComments(comments.stream()
                    .map(this::toCommentDTO)
                    .toList());
        return dto;
    }

    private CommentDTO toCommentDTO(Comment comment){
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }
}
// END
