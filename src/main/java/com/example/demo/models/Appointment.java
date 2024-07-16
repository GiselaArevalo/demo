package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private ConsultationRoom consultationRoom;

    private LocalDateTime appointmentTime;
    private String patientName;

    // Getters y setters
}
