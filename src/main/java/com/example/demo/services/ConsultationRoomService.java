package com.example.demo.services;

import com.example.demo.models.ConsultationRoom;
import com.example.demo.repositories.ConsultationRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationRoomService {

    @Autowired
    private ConsultationRoomRepository consultationRoomRepository;

    public List<ConsultationRoom> getAllRooms() {
        return consultationRoomRepository.findAll();
    }

    public ConsultationRoom saveRoom(ConsultationRoom room) {
        return consultationRoomRepository.save(room);
    }
}
