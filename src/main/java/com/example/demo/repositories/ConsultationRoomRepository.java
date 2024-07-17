package com.example.demo.repositories;

import com.example.demo.models.ConsultationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRoomRepository extends JpaRepository<ConsultationRoom, Long> {
}
