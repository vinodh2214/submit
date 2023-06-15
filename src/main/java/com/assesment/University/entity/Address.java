package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String no;
    private String street;
    private String aptNo;
    private String city;
    private String state;
    private String zipCode;
}
