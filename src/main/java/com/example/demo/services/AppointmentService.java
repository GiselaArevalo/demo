package com.example.demo.services;

import com.example.demo.models.Appointment;
import com.example.demo.models.Doctor;
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
        validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    private void validateAppointment(Appointment appointment) {
        //No se puede agendar cita en el mismo consultorio y la hora igual
        List<Appointment> existingAppointmentsInRoom = appointmentRepository.findByConsultationRoomAndAppointmentTime(
                appointment.getConsultationRoom(), appointment.getAppointmentTime());
        if (!existingAppointmentsInRoom.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una cita agendada en el mismo consultorio a la misma hora.");
        }

        //no mismo doctor misma hora
        List<Appointment> existingAppointmentsForDoctor = appointmentRepository.findByDoctorAndAppointmentTime(
                appointment.getDoctor(), appointment.getAppointmentTime());
        if (!existingAppointmentsForDoctor.isEmpty()) {
            throw new IllegalArgumentException("El doctor ya tiene una cita agendada a la misma hora.");
        }

        //sobre lo del paciente
        LocalDateTime minTimeAllowed = appointment.getAppointmentTime().minusHours(2);
        LocalDateTime maxTimeAllowed = appointment.getAppointmentTime().plusHours(2);
        List<Appointment> existingAppointmentsForPatient = appointmentRepository.findByPatientNameAndAppointmentTimeBetween(
                appointment.getPatientName(), minTimeAllowed, maxTimeAllowed);
        if (!existingAppointmentsForPatient.isEmpty()) {
            throw new IllegalArgumentException("El paciente ya tiene otra cita para el mismo día dentro del límite de tiempo permitido.");
        }

        //no tener 8 citas al dia
        List<Appointment> doctorAppointmentsToday = appointmentRepository.findByDoctorAndAppointmentTimeBetween(
                appointment.getDoctor(), appointment.getAppointmentTime().toLocalDate().atStartOfDay(),
                appointment.getAppointmentTime().toLocalDate().atTime(23, 59, 59));
        if (doctorAppointmentsToday.size() >= 8) {
            throw new IllegalArgumentException("El doctor ya tiene 8 citas agendadas para el día.");
        }
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
