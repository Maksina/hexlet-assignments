package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll(){
        return bookRepository.findAll().stream().map(bookMapper::map).toList();
    }

    public BookDTO getBookById(Long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + id));
        return bookMapper.map(book);
    }

    public BookDTO createBook(BookCreateDTO bookData){
        var book = bookMapper.map(bookData);
        var author = authorRepository.findById(book.getAuthor().getId()).orElseThrow(() -> new ConstraintViolationException(null));
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO updateBook(BookUpdateDTO bookData, Long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + id));
        bookMapper.update(bookData, book);
        var author = authorRepository.findById(book.getAuthor().getId()).orElseThrow(() -> new ConstraintViolationException(null));
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    // END
}
