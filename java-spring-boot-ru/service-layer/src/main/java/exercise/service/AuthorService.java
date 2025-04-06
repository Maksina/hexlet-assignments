package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll(){
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getAuthorById(Long id){
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + id));
        return authorMapper.map(author);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorData){
        var author = authorMapper.map(authorData);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO updateAuthor(AuthorUpdateDTO authorData, Long id){
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + id));
        authorMapper.update(authorData, author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }

    // END
}
