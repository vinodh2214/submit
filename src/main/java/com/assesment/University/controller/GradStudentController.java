package com.assesment.University.controller;

import com.assesment.University.entity.GradStudent;

import com.assesment.University.repsitory.GradStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gradStudents")
public class GradStudentController {

    private final GradStudentRepository gradStudentRepository;

    @Autowired
    public GradStudentController(GradStudentRepository gradStudentRepository) {
        this.gradStudentRepository = gradStudentRepository;
    }

    @GetMapping
    public ResponseEntity<List<GradStudent>> getAllGradStudents() {
        List<GradStudent> gradStudents = (List<GradStudent>) gradStudentRepository.findAll();
        return ResponseEntity.ok(gradStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradStudent> getGradStudentById(@PathVariable Long id) {
        Optional<GradStudent> gradStudent = gradStudentRepository.findById(id);
        return gradStudent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GradStudent> createGradStudent(@RequestBody GradStudent gradStudent) {
        GradStudent createdGradStudent = gradStudentRepository.save(gradStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGradStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradStudent> updateGradStudent(@PathVariable Long id, @RequestBody GradStudent updatedGradStudent) {
        Optional<GradStudent> gradStudent = gradStudentRepository.findById(id);
        if (gradStudent.isPresent()) {
            updatedGradStudent.setId(id);
            GradStudent savedGradStudent = gradStudentRepository.save(updatedGradStudent);
            return ResponseEntity.ok(savedGradStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradStudent(@PathVariable Long id) {
        Optional<GradStudent> gradStudent = gradStudentRepository.findById(id);
        if (gradStudent.isPresent()) {
            gradStudentRepository.delete(gradStudent.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
