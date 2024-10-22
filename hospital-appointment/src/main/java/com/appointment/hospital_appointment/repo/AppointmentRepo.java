package com.appointment.hospital_appointment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.hospital_appointment.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

//	Optional<Appointment> findByDoctormail(String name);

	Appointment findByDoctormail(String name);

	List<Appointment> findAllByDoctormail(String name);

}
