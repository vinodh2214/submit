package com.assesment.University.entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends Person{
//    @ElementCollection
//    private List<String>freshmen;
//    @ElementCollection
//    private List<String>sophmore;
    @ManyToOne
    private Department Minor;
    public void setMinor(Department minor) {

        this.Minor=minor;
    }
    @ManyToOne
    private Department Major;
    public void setMajor(Department major) {

        this.Major=major;
    }




    @ManyToMany
    private List<Current_Section> Registered;


    public void addRegistered(Current_Section current_section){
        Registered.add(current_section);
        current_section.setRegister().add(this);
    }
    public List<Current_Section> setRegistered() {
        return Registered;
    }

    @ManyToMany
    private List<Section> Transcripts;

    public void addTranscripts(Section section){
        Transcripts.add(section);
        section.setTranscript().add(this);
    }

    public List<Section> setTranscripts() {
        return Transcripts;
    }


}
