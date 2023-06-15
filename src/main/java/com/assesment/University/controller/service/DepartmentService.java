package com.assesment.University.controller.service;

import com.assesment.University.entity.Department;

public interface DepartmentService {
    Department getDepartment(Long id);
    String addDepartment(Department department);
    String updateDepartment(Department department);
}
