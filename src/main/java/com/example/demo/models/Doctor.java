package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name_paternal")
    private String lastNamePaternal;

    @Column(name = "last_name_maternal")
    private String lastNameMaternal;

    @Column(name = "specialty")
    private String specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
