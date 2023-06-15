package com.assesment.University.controller;

import com.assesment.University.entity.Section;
import com.assesment.University.repsitory.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = (List<Section>) sectionRepository.findAll();
        return ResponseEntity.ok(sections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable Long id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        return optionalSection.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Section> createSection(@RequestBody Section section) {
        Section savedSection = sectionRepository.save(section);
        return ResponseEntity.ok(savedSection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Long id, @RequestBody Section sectionDetails) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()) {
            Section section = optionalSection.get();
            section.setCurrent_year(sectionDetails.getCurrent_year());
            section.setCurrent_qtr(sectionDetails.getCurrent_qtr());


            Section updatedSection = sectionRepository.save(section);
            return ResponseEntity.ok(updatedSection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()) {
            sectionRepository.delete(optionalSection.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}