package com.assesment.University.entity;



import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String college_name;
    private String college_dean;
    private String college_office;
    @OneToMany(mappedBy = "COLLEGES_DEPARTMENT")
    private List<Department> DEPARTMENT_COLLEGES;

    public College() {

    }
}