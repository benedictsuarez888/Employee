package com.example.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName){
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}




