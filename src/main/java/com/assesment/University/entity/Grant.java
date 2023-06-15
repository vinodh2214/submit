package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name="grants")
public class Grant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String no;
    private String agency;
    private String startedDate;
    @Embedded
    private Support support;
    @ManyToOne
    @JoinColumn(name = "principal_investigator_id",foreignKey = @ForeignKey(name = "fk_grant_principal_investigator"))
    private Faculty principalInvestigator;
    @ManyToMany
    @JoinTable(
            name = "grant_instructor_researcher",
            joinColumns = @JoinColumn(name = "grant_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_researcher_id")
    )
    private List<Instructor_Researcher> instructorResearcherList = new ArrayList<>();
    public Grant() {

    }
    public Faculty getPrincipalInvestigator() {
        return principalInvestigator;
    }
    public void setprincipalInvestigator(Faculty principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public void setPrincipalInvestigator(Faculty principalInvestigator) {
    }
}
