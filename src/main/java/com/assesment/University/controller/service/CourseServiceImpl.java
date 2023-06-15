package com.assesment.University.controller.service;

import com.assesment.University.Exceptions.CourseAlreadyExistException;
import com.assesment.University.Exceptions.NoSuchCollegeExistsException;
import com.assesment.University.Exceptions.NoSuchCourseExistException;
import com.assesment.University.entity.Course;
import com.assesment.University.repsitory.CourseRepository;

public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new NoSuchCourseExistException(
                        "NO SUCH COURSE EXISTS WITH ID : " + id));
    }

    @Override
    public String addCourse(Course course) {

        Course existingCourse =
                courseRepository.findById(course.getId())
                        .orElse(null);

        if (existingCourse == null) {
            courseRepository.save(course);
            return "Course added successfully";
        } else {
            throw new CourseAlreadyExistException(
                    "College already exists");
        }
    }

    @Override
    public String updateCourse(Course course) { // new date employee
        Course existingCourse = // old college
                courseRepository.findById(course.getId())
                        .orElse(null);

        if (existingCourse == null) {
            throw new NoSuchCollegeExistsException(
                    "No Such course exists exception");
        } else {
            existingCourse.setCourse_name(course.getCourse_name());

            courseRepository.save(existingCourse); // merge - Persistence Context

            return "Employee updated successfully";
        }
    }
}
