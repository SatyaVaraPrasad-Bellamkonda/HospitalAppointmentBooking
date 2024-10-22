package com.hospital.hospital_validation.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.hospital_validation.response.AppointmentResponse;
import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.response.PatientResponse;
import com.hospital.hospital_validation.service.AppointmentService;
import com.hospital.hospital_validation.service.HospitalDoctorService;

@RestController
public class HospitalRest {
	@Autowired
	private HospitalDoctorService docService;
	@Autowired
	private AppointmentService appService;
	
//	@GetMapping("/doctors/{id}")
//	public ResponseEntity<DoctorUserValidationResponse> getEmpoyeeDetails(@PathVariable int id) {
//		DoctorUserValidationResponse emp = docService.getByDoctorId(id);
//		return ResponseEntity.status(HttpStatus.OK).body(emp);
//	}

	@PostMapping("/patientDetailsSubmit")
	public ModelAndView doctorSignUp(@ModelAttribute PatientResponse patient) {
		ModelAndView model = new ModelAndView();
		if (!patient.getPatient_username().contains("@") && docService.signupPatient(patient)) {
			model.setViewName("login");
			model.addObject("success", "Successfully Registered....!");
		return model;
	} else if (patient.getPatient_username().contains("@")) {
		model.setViewName("login");
		model.addObject("errorMessage", "Username Does Not Contain @ Value");
		model.addObject("showSignup", true);
		return model;
		}
		
		else {
//		model.setViewName("errorSignUp");
//		model.addObject("name", patient.getPatient_username());
//		return model;
		model.setViewName("login");
		model.addObject("name", patient.getPatient_name());
		model.addObject("mobile", patient.getPatient_mobile());
		model.addObject("place", patient.getPatient_place());
		model.addObject("gender", patient.getPatient_gender());
		model.addObject("age", patient.getPatient_age());
		model.addObject("weight", patient.getPatient_weight());
		model.addObject("temp", patient.getPatient_temp());
		model.addObject("errorMessage", "Error...! Username Already Exist.");
		model.addObject("showSignup", true);
		return model;
	}
	}
	
	
	
	@GetMapping("/showPatientProfile")
	public ModelAndView getPatientProfile(@RequestParam("patient_name") String name) {
		PatientResponse emp = docService.findPatientByUsername(name);
		ModelAndView model = new ModelAndView();
		model.setViewName("patientProfile");
		model.addObject("patient", emp);
		String imageBytes = appService.extracted(name);

		if (imageBytes != "failed") {
			model.addObject("image", imageBytes);
			model.addObject("imageType", "image/jpeg");
		} else {
			model.addObject("error", "Image not found");
		}

		return model;
	}

	
		@PostMapping("/approvePatientById")
		public ModelAndView approvePatientAppointment(Principal principal, @RequestParam("id") int id,
				@RequestParam("status") int status) {
//			System.out.println(status);
			ModelAndView model = new ModelAndView();
			if (appService.updateAppointment(id, status)) {
				List<AppointmentResponse> response = appService.getAppointments(principal.getName());
//				System.out.println(response.getDoctormail());
//				for(AppointmentResponse res:response) {
//					System.out.println(res.getPatient_name());
//				}
				model.setViewName("dashboard");
				model.addObject("appointments", response);
				model.addObject("data", "d");
				return model;

			} else {
				model.setViewName("errorSignUp");
				model.addObject("name", "error");
				return model;
		}
//		System.out.println(appointment.getDate());
		}

		

}
