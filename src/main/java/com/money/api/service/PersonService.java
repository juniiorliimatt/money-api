package com.money.api.service;

import com.money.api.model.Person;
import com.money.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class PersonService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private PersonRepository repository;

  public Person update(Long code, Person person) {
    var personFromBD = getPersonByCode(code);
    BeanUtils.copyProperties(person, personFromBD, "code");
    return repository.save(person);
  }

  public void updatePropertieActive(Long code, Boolean active) {
    var person = getPersonByCode(code);
    person.setActive(active);
    repository.save(person);
  }

  public Person getPersonByCode(Long code) {
    Optional<Person> personFromBD = repository.findById(code);
    if (personFromBD.isEmpty()) {
      throw new EmptyResultDataAccessException(1);
    }
    return personFromBD.get();
  }
}
