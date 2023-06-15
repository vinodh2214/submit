package com.assesment.University.controller.service;

import com.assesment.University.Exceptions.CollegeAlreadyExistException;
import com.assesment.University.Exceptions.NoSuchDepartmentExistException;
import com.assesment.University.entity.Department;
import com.assesment.University.repsitory.DepartmentRepository;

public class DepartmentServiceImpl {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new NoSuchDepartmentExistException(
                        "NO SUCH DEPARTMENT EXISTS WITH ID : " + id));
    }


    public String addDepartment(Department department) {

        Department existingDepartment =
                departmentRepository.findById(department.getId())
                        .orElse(null);

        if (existingDepartment == null) {
            departmentRepository.save(department);
            return "Department added successfully";
        } else {
            throw new CollegeAlreadyExistException(
                    "College already exists");
        }
    }


    public String updateDepartment(Department department) { // new date employee
        Department existingDepartment = // old college
                departmentRepository.findById(department.getId())
                        .orElse(null);

        if (existingDepartment == null) {
            throw new NoSuchDepartmentExistException(
                    "No Such Department exists exception");
        } else {
            existingDepartment.setDepartment_name(department.getDepartment_name());

            departmentRepository.save(existingDepartment); // merge - Persistence Context

            return "Department updated successfully";
        }
    }
}
