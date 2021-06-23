package com.money.api.service;

import com.money.api.model.Launch;
import com.money.api.model.Person;
import com.money.api.repository.LaunchRepository;
import com.money.api.repository.PersonRepository;
import com.money.api.service.exception.PersonNonexistentOrInactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Service
public class LaunchService implements Serializable {

  @Autowired
  private LaunchRepository repository;

  @Autowired
  private PersonRepository personRepository;

  @Transactional
  public Launch save(Launch launch) {
    Optional<Person> personFromBD = personRepository.findById(launch.getPerson().getCode());
    if (personFromBD.isEmpty() || personFromBD.get().isInativo()) {
      throw new PersonNonexistentOrInactive();
    }
    return repository.save(launch);
  }
}
