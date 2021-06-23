package com.money.api.resource;

import com.money.api.event.CreatedEventResource;
import com.money.api.model.Launch;
import com.money.api.repository.LaunchRepository;
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
@RequestMapping("/api/launch")
public class LaunchResource {

  @Autowired
  private LaunchRepository repository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @Transactional
  @GetMapping
  public ResponseEntity<List<Launch>> findAll() {
    List<Launch> launches = repository.findAll();
    return launches.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(launches);
  }

  @Transactional
  @GetMapping("/{code}")
  public ResponseEntity<Optional<Launch>> findByCode(@PathVariable Long code) {
    var fromBD = repository.findById(code);
    return fromBD.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fromBD);
  }

  @Transactional
  @PostMapping
  public ResponseEntity<Launch> save(@Valid @RequestBody Launch launch, HttpServletResponse response) {
    var saved = repository.save(launch);
    publisher.publishEvent(new CreatedEventResource(this, response, saved.getCode()));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }
}
