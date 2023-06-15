package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Current_Section")
@Table(name="current_section")
public class Current_Section extends Section{
    @ManyToMany(mappedBy = "Registered")
    private List<Student> Register;

    public void addRegister(Student student){
        Register.add(student);
        student.setRegistered().add(this);
    }
    public List<Student> setRegister() {
        return Register;
    }

}
