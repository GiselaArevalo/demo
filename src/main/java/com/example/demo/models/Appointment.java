package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultation_room_id")
    private ConsultationRoom consultationRoom;

    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

    @Column(name = "patient_name")
    private String patientName;
}
