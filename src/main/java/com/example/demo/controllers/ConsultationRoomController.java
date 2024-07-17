package com.example.demo.controllers;

import com.example.demo.models.ConsultationRoom;
import com.example.demo.services.ConsultationRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConsultationRoomController {

    @Autowired
    private ConsultationRoomService consultationRoomService;

    @GetMapping("/consultation-rooms")
    public String getAllRooms(Model model) {
        List<ConsultationRoom> rooms = consultationRoomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "consultationRooms";
    }
}
