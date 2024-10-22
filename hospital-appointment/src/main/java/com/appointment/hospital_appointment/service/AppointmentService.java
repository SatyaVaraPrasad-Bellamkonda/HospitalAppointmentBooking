package com.appointment.hospital_appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.appointment.hospital_appointment.entity.Appointment;
import com.appointment.hospital_appointment.repo.AppointmentRepo;
import com.appointment.hospital_appointment.response.AppointmentResponse;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepo repo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ModelMapper modelMapper;

	public AppointmentResponse findAddressByEmployeeId(int id) {
		Optional<Appointment> address = repo.findById(id);
		AppointmentResponse addressResponse = modelMapper.map(address, AppointmentResponse.class);
		return addressResponse;
		
//		return appointments.stream()
//                .map(appointment -> modelMapper.map(appointment, AppointmentResponse.class))
//                .collect(Collectors.toList());
	}

	public AppointmentResponse addAppointment(AppointmentResponse appointment) {
		Appointment address = modelMapper.map(appointment, Appointment.class);
		String url="SELECT SUM(CAST(duration AS UNSIGNED)) AS total_duration FROM hospital.appointment where doctormail=? and date=? and status!=3;";
		Object[] obj= {address.getDoctormail(),address.getDate()};
		int val=jdbcTemplate.queryForObject(url,obj,Integer.class);
//		System.out.println(val);
		
		int c=Integer.parseInt(appointment.getDuration());
		if(val+c<=361) {
		Appointment savedAddress = repo.save(address);
		AppointmentResponse map = modelMapper.map(savedAddress, AppointmentResponse.class);
		return map;
		}
		return null;
	}

	public List<Appointment> findAllDoctors() {
		return repo.findAll();
	}

	public List<Appointment> filterDoctorsByName(String name) {
		List<Appointment> allData = findAllDoctors();
		List<Appointment> filteredData = new ArrayList<>();
//		String res = name.toLowerCase();
		for (Appointment doctor : allData) {
			if (doctor.getDoctormail() != null && doctor.getDoctormail().equals(name)) {
				filteredData.add(doctor);
			}
		}
		return filteredData;
	}

	public List<Appointment> filterByPatientName(String name) {
		List<Appointment> allData = findAllDoctors();
		List<Appointment> filteredData = new ArrayList<>();
//		String res = name.toLowerCase();
		for (Appointment patient : allData) {
			if (patient.getPatient_name() != null && patient.getPatient_name().equals(name)) {
				filteredData.add(patient);

			}
		}
		return filteredData;
	}

	public void update(int id, int status) {
		String updateQuery = "UPDATE appointment SET status=? WHERE id = ?";
		Object[] args = { status, id };
		jdbcTemplate.update(updateQuery, args);

	}

	public List<Appointment> allAppointments() {
		List<Appointment> allData = repo.findAll();		
		return allData;
	}

//	public void withdraw(int id) {
//		String updateQuery = "UPDATE appointment SET status=? WHERE id = ?";
//		Object[] args = { id };
//		jdbcTemplate.update(updateQuery, args);
//
//	}



}
