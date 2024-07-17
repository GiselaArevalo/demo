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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ConsultationRoomService consultationRoomService;

    //all
    @GetMapping("/")
    public String listAppointments(Model model,
                                   @RequestParam(required = false) LocalDate date,
                                   @RequestParam(required = false) Long consultationRoomId,
                                   @RequestParam(required = false) Long doctorId) {
        List<Appointment> appointments = appointmentService.filterAppointments(date, consultationRoomId, doctorId);
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<ConsultationRoom> rooms = consultationRoomService.getAllRooms();
        model.addAttribute("appointments", appointments);
        model.addAttribute("doctors", doctors);
        model.addAttribute("rooms", rooms);
        return "appointments";
    }

    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<ConsultationRoom> rooms = consultationRoomService.getAllRooms();
        model.addAttribute("doctors", doctors);
        model.addAttribute("rooms", rooms);
        model.addAttribute("appointment", new Appointment());
        return "newAppointment";
    }

    @PostMapping("/new")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments/";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments/";
    }
}
