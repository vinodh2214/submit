package com.assesment.University.controller.service;

import com.assesment.University.entity.Course;

public interface CourseService {
    Course getCourse(Long id);
    String addCourse(Course course);
    String updateCourse(Course college);
}
