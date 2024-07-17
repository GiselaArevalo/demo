package com.example.demo.controllers;

import com.example.demo.models.Appointment;
import com.example.demo.models.Doctor;
import com.example.demo.models.ConsultationRoom;
import com.example.demo.services.AppointmentService;
import com.example.demo.services.DoctorService;
import com.example.demo.services.ConsultationRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ConsultationRoomService consultationRoomService;

    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/new-appointment")
    public String newAppointmentForm(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<ConsultationRoom> rooms = consultationRoomService.getAllRooms();
        model.addAttribute("doctors", doctors);
        model.addAttribute("rooms", rooms);
        model.addAttribute("appointment", new Appointment());
        return "newAppointment";
    }

    @PostMapping("/save-appointment")
    public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }
}
