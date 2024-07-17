package com.example.demo.controllers;

import com.example.demo.models.Doctor;
import com.example.demo.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @GetMapping("/new")
    public String showDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "newDoctor";
    }

    @PostMapping("/new")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "editDoctor";
    }

    @PostMapping("/edit/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") Doctor doctor, Model model) {
        doctor.setId(id);
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors/";
    }
}
