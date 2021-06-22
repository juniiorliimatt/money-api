package com.money.api.service;

import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class PersonService implements Serializable {

  @Autowired
  private PersonRepository repository;

  public Optional<Person> update(Long code, Person person) {
    Optional<Person> personFromBD = repository.findById(code);
    if (personFromBD.isPresent()) {
      BeanUtils.copyProperties(person, personFromBD.get(), "code");
      return personFromBD;
    }
    return Optional.empty();
  }
}
