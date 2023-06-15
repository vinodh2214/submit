package com.assesment.University.controller;

import com.assesment.University.entity.College;
import com.assesment.University.repsitory.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/colleges")
public class CollegeController {

    private final CollegeRepository collegeRepository;

    @Autowired
    public CollegeController(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @GetMapping
    public ResponseEntity<List<College>> getAllColleges() {
        List<College> colleges = (List<College>) collegeRepository.findAll();
        return ResponseEntity.ok(colleges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
        Optional<College> college = collegeRepository.findById(id);
        return college.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<College> createCollege(@RequestBody College college) {
        College savedCollege = collegeRepository.save(college);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCollege);
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable Long id, @RequestBody College collegeDetails) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            college.setCollege_name(collegeDetails.getCollege_name());
            college.setCollege_dean(collegeDetails.getCollege_dean());
            college.setCollege_office(collegeDetails.getCollege_office());

            College updatedCollege = collegeRepository.save(college);
            return ResponseEntity.ok(updatedCollege);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id) {
        Optional<College> optionalCollege = collegeRepository.findById(id);
        if (optionalCollege.isPresent()) {
            College college = optionalCollege.get();
            collegeRepository.delete(college);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//@GetMapping("/colleges/new")
//public String showCollegeForm(Model model) {
//    model.addAttribute("college", new College());
//    return "collegeForm";
//}
//
//    @PostMapping("/colleges")
//    public String createCollege(@Valid @ModelAttribute("college") College college, BindingResult result) {
//        if (result.hasErrors()) {
//            return "collegeForm";
//        }
//
//        collegeRepository.save(college);
//
//        return "redirect:/colleges/new";
//    }
//
}
