package com.appointment.hospital_appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.hospital_appointment.entity.Appointment;
import com.appointment.hospital_appointment.response.AppointmentResponse;
import com.appointment.hospital_appointment.service.AppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private AppointmentService validation;

	@GetMapping("/user/{id}")
	public ResponseEntity<AppointmentResponse> getAddressByEmployeeId(@PathVariable int id) {
		AppointmentResponse addressResponse = validation.findAddressByEmployeeId(id);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}

	@PostMapping("/addAppointment")
	public String addAppointment(@RequestBody AppointmentResponse appointment, Model model) {
//		System.out.println(
//				"Recieved Data" + addressResponse.getPatient_name() + "  " + addressResponse.getPatient_password());
		AppointmentResponse savedAddress = validation.addAppointment(appointment);
		if(savedAddress==null) {
			return "nodata";
		}
		return "success";
	}

	@GetMapping("/doctor/{doctorname}")
	public ResponseEntity<List<Appointment>> getUserByUsername(@PathVariable String doctorname) {
		List<Appointment> addressResponse = validation.filterDoctorsByName(doctorname);
//		System.out.println(addressResponse.);
		return ResponseEntity.ok(addressResponse);
	}
	@GetMapping("/allAppointments")
	public ResponseEntity<List<Appointment>> allAppointments() {
		List<Appointment> addressResponse = validation.allAppointments();
//		System.out.println(addressResponse.);
		return ResponseEntity.ok(addressResponse);
	}

	@GetMapping("/patient/{patientname}")
	public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable String patientname) {
		List<Appointment> addressResponse = validation.filterByPatientName(patientname);
//		System.out.println(addressResponse.);
		return ResponseEntity.ok(addressResponse);
	}

	@PutMapping("/update/{id}/{status}")
	public void updateData(@PathVariable int id, @PathVariable int status) {
		validation.update(id, status);
	}

//	@DeleteMapping("/withdraw/{id}")
//	public void deleteData(@PathVariable int id) {
//		validation.delete(id);
//	}

}
