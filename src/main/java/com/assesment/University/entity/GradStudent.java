package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("GRADSTUDENT")
public class GradStudent extends Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Degree> degrees;

    public GradStudent() {
        degrees = new ArrayList<>();
    }
    public void addDegree(Degree degree) {
        degrees.add(degree);
    }
    @ManyToOne
    private Faculty advisor;
    @ManyToMany
    private List<Faculty> committees;
    public void setAdvisor(Faculty advisor) {
        this.advisor = advisor;
    }
    public void addCommitteeMember(Faculty faculty) {
        committees.add(faculty);
        faculty.getCommitteeMembers().add(this);
    }
    public List<Faculty> getCommitteeMembers() {
        return committees;
    }
}
