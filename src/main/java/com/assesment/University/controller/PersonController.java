package com.assesment.University.controller;


import com.assesment.University.entity.Person;
import com.assesment.University.repsitory.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = (List<Person>) personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Person> changePerson(@PathVariable Long id, @RequestBody Person changePerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setName(changePerson.getName());
            Person savedPerson = personRepository.save(person);
            return new ResponseEntity<>(savedPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person updatedPerson) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            updatedPerson.setId(id);
            Person savedPerson = personRepository.save(updatedPerson);
            return ResponseEntity.ok(savedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with id: " + id);
        }
    }
//@GetMapping("/persons/new")
//public String showPersonForm(Model model) {
//    model.addAttribute("person", new Person());
//    return "personForm";
//}
//
//    @PostMapping("/persons")
//    public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "personForm";
//        }
//
//        personRepository.save(person);
//
//        redirectAttributes.addFlashAttribute("successMessage", "Details entered successfully.");
//        return "redirect:/persons/new";
//    }
//@GetMapping("/persons/add")
//public String showAddPersonForm(Model model) {
//    model.addAttribute("person", new Person());
//    return "person-form"; // Assuming person-form.html exists in the templates directory
//}
//
//    @PostMapping("/add")
//    public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult result, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "person-form";
//        }
//
//        personRepository.save(person);
//
//        redirectAttributes.addFlashAttribute("successMessage", "Details entered successfully.");
//        return "redirect:/persons/success";
//    }
//
//    @GetMapping("/success")
//    public String showSuccessPage() {
//        return "success"; // Assuming success.html exists in the templates directory
//    }
}
