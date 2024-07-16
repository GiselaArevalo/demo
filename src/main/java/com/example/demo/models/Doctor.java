package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastNamePaternal;
    private String lastNameMaternal;
    private String specialty;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    
}
