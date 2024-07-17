package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "consultationRooms")
@Data
@NoArgsConstructor
public class ConsultationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "floor")
    private int floor;

    @OneToMany(mappedBy = "consultationRoom", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
