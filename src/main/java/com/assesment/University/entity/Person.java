package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Person")
@Table(name="person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> ssn;

    private LocalDate bDate;

    private String sex;

    @Embedded
    private Name name;

    @Embedded
    private Address address;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Faculty> faculties;

    public <T> Person(List<T> list, LocalDate now, String male, Name name, Address address) {
    }
}
