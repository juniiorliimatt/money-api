package com.money.api.resource;

import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonResourse {

  @Autowired
  private PersonRepository repository;

  @Transactional
  @GetMapping
  public ResponseEntity<List<Person>> findAll() {
    List<Person> categories = repository.findAll();
    return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
  }

  @GetMapping("/{code}")
  public ResponseEntity<Optional<Person>> getByCode(@PathVariable Long code) {
    var fromBd = repository.findById(code);
    return fromBd.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fromBd);
  }

  @PostMapping
  public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {
    Person saved = repository.save(person);
    var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(saved.getId()).toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(saved);
  }
}
