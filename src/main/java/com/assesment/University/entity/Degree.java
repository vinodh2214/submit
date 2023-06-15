package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Degree {
    private String college;
    private String degree;
    private int year;
}
