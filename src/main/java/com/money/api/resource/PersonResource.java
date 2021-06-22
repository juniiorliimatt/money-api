package com.money.api.resource;

import com.money.api.event.CreatedEventResource;
import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;
import com.money.api.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

  @Autowired
  private PersonRepository repository;

  @Autowired
  private PersonService service;

  @Autowired
  private ApplicationEventPublisher publisher;

  @Transactional
  @GetMapping
  public ResponseEntity<List<Person>> findAll() {
    List<Person> categories = repository.findAll();
    return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
  }

  @Transactional
  @GetMapping("/{code}")
  public ResponseEntity<Optional<Person>> getByCode(@PathVariable Long code) {
    var fromBd = repository.findById(code);
    return fromBd.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fromBd);
  }

  @Transactional
  @PostMapping
  public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {
    var saved = repository.save(person);
    publisher.publishEvent(new CreatedEventResource(this, response, saved.getCode()));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @Transactional
  @PutMapping("/{code}")
  public ResponseEntity<Person> update(@PathVariable Long code, @Valid @RequestBody Person person) {
    Optional<Person> fromBD = service.update(code, person);
    if(fromBD.isEmpty()){
      throw new EmptyResultDataAccessException(1);
    }
    return ResponseEntity.ok(fromBD.get());
  }

  @Transactional
  @DeleteMapping("/{code}")
  public ResponseEntity<Person> delete(@PathVariable Long code) {
    repository.deleteById(code);
    return ResponseEntity.noContent().build();
  }
}
