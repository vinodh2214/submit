package com.assesment.University.controller;

import com.assesment.University.entity.Faculty;
import com.assesment.University.repsitory.FacultyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return ResponseEntity.ok(faculty.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyRepository.save(faculty);
        return ResponseEntity.ok(savedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Optional<Faculty> existingFaculty = facultyRepository.findById(id);
        if (existingFaculty.isPresent()) {
            faculty.setId(id);
            Faculty updatedFaculty = facultyRepository.save(faculty);
            return ResponseEntity.ok(updatedFaculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.delete(faculty.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
