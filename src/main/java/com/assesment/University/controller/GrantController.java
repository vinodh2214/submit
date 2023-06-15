package com.assesment.University.controller;

import com.assesment.University.entity.Grant;
import com.assesment.University.repsitory.GrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/grants")
public class GrantController {
    private final GrantRepository grantRepository;

    @Autowired
    public GrantController(GrantRepository grantRepository) {
        this.grantRepository = grantRepository;
    }

    @GetMapping
    public Iterable<Grant> getAllGrants() {
        return grantRepository.findAll();
    }

    @GetMapping("/grants/{id}")
    public ResponseEntity<?> getGrantById(@PathVariable Long id) {
        Optional<Grant> grant = grantRepository.findById(id);
        if (grant.isPresent()) {
            return ResponseEntity.ok(grant.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/grants")
    public ResponseEntity<?> createGrant(@RequestBody Grant grant) {
        Grant createdGrant = grantRepository.save(grant);
        return ResponseEntity.ok(createdGrant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grant> updateGrant(@PathVariable Long id, @RequestBody Grant grantData) {
        Optional<Grant> optionalGrant = grantRepository.findById(id);

        if (optionalGrant.isPresent()) {
            Grant grant = optionalGrant.get();
            grant.setTitle(grantData.getTitle());
            grant.setNo(grantData.getNo());
            grant.setAgency(grantData.getAgency());
            grant.setStartedDate(grantData.getStartedDate());
            grant.setSupport(grantData.getSupport());
            grant.setPrincipalInvestigator(grantData.getPrincipalInvestigator());

            Grant updatedGrant = grantRepository.save(grant);
            return ResponseEntity.ok(updatedGrant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrant(@PathVariable Long id) {
        Optional<Grant> optionalGrant = grantRepository.findById(id);

        if (optionalGrant.isPresent()) {
            Grant grant = optionalGrant.get();
            grantRepository.delete(grant);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
