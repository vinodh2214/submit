package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Faculty")
@Table(name="faculty")
@DiscriminatorValue("FACULTY")
public class Faculty extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rank;
    private String fOffice;
    private String fPhone;
    private double salary;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToMany(mappedBy = "advisor")
    private List<GradStudent> advisors;
    @ManyToMany(mappedBy = "committees")
    private List<GradStudent> committeeMembers;
    @OneToMany(mappedBy = "principalInvestigator", cascade = CascadeType.ALL)
    private List<Grant> grants ;
    public void addGrant(Grant grant) {
        grants.add(grant);
        grant.setprincipalInvestigator(this);
    }

    public void addAdvisedStudent(GradStudent gradStudent) {
        advisors.add(gradStudent);
        gradStudent.setAdvisor(this);
    }
    public void addCommitteeMember(GradStudent gradStudent) {
        committeeMembers.add(gradStudent);
        gradStudent.getCommitteeMembers().add(this);
    }
    public List<GradStudent> getCommitteeMembers() {
        return committeeMembers;
    }
    @ManyToMany
    @JoinColumn(name = "department_id")
    private List<Department> Belongs;

    public void addBelongs(Department department){
        Belongs.add(department);
        department.getBelongs_to().add(this);
    }

    @OneToOne
    private Department Chairs;
    public void addChairs(Department department){
        department.getChair();
    }
    public Department getChairs() {
        return Chairs;
    }

}
