package com.money.api.resource;

import com.money.api.model.Category;
import com.money.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryResourse implements Serializable {

  @Autowired
  private CategoryRepository repository;

  @Transactional
  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = repository.findAll();
    return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
  }

  @GetMapping("/{code}")
  public ResponseEntity<Optional<Category>> getByCode(@PathVariable Long code) {
    var fromBd = repository.findById(code);
    return fromBd.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fromBd);

  }

  @PostMapping
  public ResponseEntity<Category> save(@Valid @RequestBody Category category, HttpServletResponse response) {
    Category saved = repository.save(category);
    var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(saved.getCode()).toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(saved);
  }
}
