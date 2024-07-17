package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ConsultationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private int floor;

    @OneToMany(mappedBy = "consultationRoom")
    private List<Appointment> appointments;

}
