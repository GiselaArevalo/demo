package com.example.demo.services;

import com.example.demo.models.Appointment;
import com.example.demo.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAppointmentsByDoctorAndTime(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, start, end);
    }

    public List<Appointment> findAppointmentsByRoomAndTime(Long roomId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByConsultationRoomIdAndAppointmentTimeBetween(roomId, start, end);
    }

    public List<Appointment> findAppointmentsByPatientAndTime(String patientName, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByPatientNameAndAppointmentTimeBetween(patientName, start, end);
    }
}
