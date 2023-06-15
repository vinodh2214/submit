package com.assesment.University.controller.service;

import com.assesment.University.entity.Person;

import java.util.Optional;

public interface PersonService {
    Optional<Person> getPerson(Long id);
    String addPerson(Person person);
    String updatePerson(Person person);
}
