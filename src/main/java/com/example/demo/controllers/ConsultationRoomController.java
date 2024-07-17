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

    @GetMapping("/")
    public String getAllRooms(Model model) {
        List<ConsultationRoom> rooms = consultationRoomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "consultationRooms";
    }

    @GetMapping("/new")
    public String showRoomForm(Model model) {
        model.addAttribute("room", new ConsultationRoom());
        return "newConsultationRoom";
    }

    @PostMapping("/new")
    public String saveRoom(@ModelAttribute("room") ConsultationRoom room, Model model) {
        consultationRoomService.saveRoom(room);
        return "redirect:/consultationRooms/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ConsultationRoom room = consultationRoomService.getRoomById(id);
        model.addAttribute("room", room);
        return "editConsultationRoom";
    }

    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id, @ModelAttribute("room") ConsultationRoom room, Model model) {
        room.setId(id);
        consultationRoomService.saveRoom(room);
        return "redirect:/consultationRooms/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        consultationRoomService.deleteRoom(id);
        return "redirect:/consultationRooms/";
    }
}
