package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long course_no;
    private String course_name;
    private String course_desc;


    @OneToMany(mappedBy = "Course_Section")
    private List<Section> Section_Course;

    public void addSection_Course(Section section) {
        Section_Course.add(section);
        section.setCourse_Section(this);
    }

    @ManyToOne
    private Department Department_c;

    public void addDepartment_c(Department department){
        this.Department_c=department;
    }

}

