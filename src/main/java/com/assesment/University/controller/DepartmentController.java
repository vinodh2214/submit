package com.assesment.University.controller;

import com.assesment.University.entity.Department;
import com.assesment.University.repsitory.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(savedDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setDepartment_name(departmentDetails.getDepartment_name());
            department.setDepartment_office(departmentDetails.getDepartment_office());
            department.setDepartment_no(departmentDetails.getDepartment_no());


            Department updatedDepartment = departmentRepository.save(department);
            return ResponseEntity.ok(updatedDepartment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            departmentRepository.delete(optionalDepartment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

