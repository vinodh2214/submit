package com.assesment.University.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Name {
    private String fName;
    private String mName;
    private String lName;
}
