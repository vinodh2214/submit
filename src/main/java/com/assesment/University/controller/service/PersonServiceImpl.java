package com.assesment.University.controller.service;

import com.assesment.University.Exceptions.NoSuchPersonExistsException;
import com.assesment.University.Exceptions.PersonAlreadyExistException;
import com.assesment.University.entity.Person;
import com.assesment.University.repsitory.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    private PersonServiceImpl(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    @Override
    public Optional<Person> getPerson(Long id) {
        return Optional.ofNullable(personRepository.findById(id).orElseThrow(
                () -> new NoSuchPersonExistsException("NO SUCH PERSON EXISTS WITH ID : "+id)
        ));
    }

    @Override
    public String addPerson(Person person) {
        Person existingPerson = personRepository.findById(person.getId()).orElse(null);

        if(existingPerson == null) {
            personRepository.save(person);
            return "Person added successfully";
        }else{
            throw new PersonAlreadyExistException("Person already exists");
        }
    }

    @Override
    public String updatePerson(Person person) { //new date person
        Person existingPerson = //old person
                personRepository.findById(person.getId()).orElse(null);

        if(existingPerson == null) {
            throw new NoSuchPersonExistsException("NO SUCH PERSON EXISTS EXCEPTION");
        }else{
            existingPerson.setName(person.getName());
            existingPerson.setAddress(person.getAddress());
            personRepository.save(existingPerson); //merge
            return "Person updated successfully";
        }
    }
}
