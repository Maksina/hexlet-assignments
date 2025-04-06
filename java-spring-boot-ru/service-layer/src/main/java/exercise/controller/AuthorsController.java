package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.model.Author;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDTO>> index() {
        var authors = authorService.getAll();
        return ResponseEntity.ok().body(authors);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> show(@PathVariable Long id){
        var author = authorService.getAuthorById(id);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@Valid @RequestBody AuthorCreateDTO data){
        return authorService.createAuthor(data);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> update(@Valid @RequestBody AuthorUpdateDTO data, @PathVariable Long id){
        var author = authorService.updateAuthor(data, id);
        return ResponseEntity.ok().body(author);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        authorService.deleteAuthor(id);
    }

    // END
}
