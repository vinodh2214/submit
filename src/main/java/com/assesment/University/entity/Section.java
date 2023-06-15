package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Section")
@Table(name="section")
public class Section {
    private String Current_year;
    private int Current_qtr;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long section_no;

    @ManyToMany(mappedBy = "Transcripts")
    private List<Student> Transcript;

    public void addTranscript(Student student){
        Transcript.add(student);
        student.setTranscripts().add(this);
    }

    public List<Student> setTranscript() {
        return Transcript;
    }

    @ManyToOne
    private Course Course_Section ;
    public void setCourse_Section(Course course_section) {

        this.Course_Section=course_section;
    }
    @ManyToOne
    private Instructor_Researcher teaches;

    public void setteaches(Instructor_Researcher researcher){
        this.teaches=researcher;
    }

}
