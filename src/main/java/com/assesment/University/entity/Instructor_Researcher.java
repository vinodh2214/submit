package com.assesment.University.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("INSTRUCTOR_RESEARCHER")
public class Instructor_Researcher extends Person {
    private String rank;
    private String office;
    private String phone;
    private double salary;
    @ManyToMany(mappedBy = "instructorResearcherList")
    private List<Grant> supporterlist = new ArrayList<>();
    @OneToMany(mappedBy = "teaches")
    private List<Section> teach=new ArrayList<>();
}
