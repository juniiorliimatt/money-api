package com.money.api.resource;

import com.money.api.event.CreatedEventResource;
import com.money.api.exceptionhandler.Erro;
import com.money.api.model.Launch;
import com.money.api.repository.LaunchRepository;
import com.money.api.service.LaunchService;
import com.money.api.service.exception.PersonNonexistentOrInactive;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/launch")
public class LaunchResource {

  @Autowired
  private LaunchRepository repository;

  @Autowired
  private LaunchService service;

  @Autowired
  private ApplicationEventPublisher publisher;

  @Autowired
  private MessageSource message;

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

  @PostMapping
  public ResponseEntity<Launch> save(@Valid @RequestBody Launch launch, HttpServletResponse response) {
    var saved = service.save(launch);
    publisher.publishEvent(new CreatedEventResource(this, response, saved.getCode()));
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @ExceptionHandler({PersonNonexistentOrInactive.class})
  public ResponseEntity<Object> handlePeopleNonexistentOrInactive(PersonNonexistentOrInactive ex) {
    var messageUser = message.getMessage("person.nonexistent-or-inactive", null,
      LocaleContextHolder.getLocale());
    var messageDev = ex.toString();
    List<Erro> erros = Collections.singletonList(new Erro(messageUser, messageDev));
    return ResponseEntity.badRequest().body(erros);
  }
}
