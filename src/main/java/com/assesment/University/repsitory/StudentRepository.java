package com.assesment.University.repsitory;

import com.assesment.University.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
