package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public List<ProductDTO> index(){
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id){
        return productMapper.map(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found")));
    }

    @PostMapping(path = "")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductCreateDTO data){
        var product = productMapper.map(data);
        var category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new ConstraintViolationException(null));
            product.setCategory(category);
            productRepository.save(product);
            var result = productMapper.map(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);


    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductUpdateDTO data, @PathVariable long id){
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        productMapper.update(data, product);

           var category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Not found"));

        product.setCategory(category);
        productRepository.save(product);
        var result = productMapper.map(product);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        productRepository.deleteById(id);
    }
    // END
}
