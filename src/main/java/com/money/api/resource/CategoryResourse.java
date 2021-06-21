package com.money.api.resource;

import com.money.api.model.Category;
import com.money.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryResourse implements Serializable {

  @Autowired
  private CategoryRepository repository;

  @Transactional
  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = repository.findAll();
    return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
  }

  @PostMapping
  public ResponseEntity<Category> save(@RequestBody Category category, HttpServletResponse response) {
    Category saved = repository.save(category);
    var URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(saved.getCode()).toUri();
    response.setHeader("Location", URI.toASCIIString());
    return ResponseEntity.created(URI).build();
  }
}
