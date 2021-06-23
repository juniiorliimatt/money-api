package com.money.api.resource;

import com.money.api.event.CreatedEventResource;
import com.money.api.model.Category;
import com.money.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

  @Autowired
  private CategoryRepository repository;

  @Autowired
  private ApplicationEventPublisher publisher;

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

  @Transactional
  @PostMapping
  public ResponseEntity<Category> save(@Valid @RequestBody Category category, HttpServletResponse response) {
    var saved = repository.save(category);
    publisher.publishEvent(new CreatedEventResource(this, response, saved.getCode()));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }
}
