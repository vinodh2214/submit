package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String department_name;
    private String department_office;
    private long department_no;
    @OneToMany(mappedBy = "Minor")
    private List<Student> minors;

    public Department() {

    }

    public void addMinor(Student student) {
        minors.add(student);
        student.setMinor(this);
    }

    @OneToMany(mappedBy = "Major")
    private List<Student> majors;

    public void addMajor(Student student) {
        majors.add(student);
        student.setMajor(this);
    }

    @ManyToMany(mappedBy = "Belongs")
    private List<Faculty> Belongs_to;

    public void addBelongs_to(Faculty faculty) {
        Belongs_to.add(faculty);
        faculty.getBelongs().add(this);
    }

    @OneToOne(mappedBy = "Chairs", cascade = CascadeType.ALL)
    private Faculty Chair;

    public void addChair(Faculty faculty) {
        faculty.getChairs();
    }


    @OneToMany(mappedBy = "Department_c")
    private List<Course> D_courses;
    public void addD_courses(Course courses){
        D_courses.add(courses);
        courses.getDepartment_c();
    }
    @ManyToOne
    private College COLLEGES_DEPARTMENT;

    public void addD_Colleges(College college)
    {
        this.COLLEGES_DEPARTMENT=college;

    }

}
