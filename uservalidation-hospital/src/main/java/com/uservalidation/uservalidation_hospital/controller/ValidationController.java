package com.uservalidation.uservalidation_hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uservalidation.uservalidation_hospital.entity.DoctorUserValidation;
import com.uservalidation.uservalidation_hospital.response.DoctorUserValidationResponse;
import com.uservalidation.uservalidation_hospital.service.ValidationServiceImpl;

@RestController
public class ValidationController {

	@Autowired
	private ValidationServiceImpl validation;

	@GetMapping("/doctor/{id}")
	public ResponseEntity<DoctorUserValidationResponse> getAddressByEmployeeId(@PathVariable int id) {
		DoctorUserValidationResponse addressResponse = validation.findAddressByEmployeeId(id);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	@GetMapping("/doctorValidation/{username}")
	public ResponseEntity<DoctorUserValidationResponse> getUserByUsername(@PathVariable String username) {
		DoctorUserValidationResponse addressResponse=validation.findUser(username);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}

	@PostMapping("/addFromAnother")
	public void addAddressFromAnother(@RequestBody DoctorUserValidationResponse addressResponse) {
//		System.out.println("Recieved Data" + addressResponse.getName() + "  " + addressResponse.getPassword());
		DoctorUserValidationResponse savedAddress = validation.addDoctor(addressResponse);

	}

	@PostMapping("/update")
	public void updateData(@RequestBody DoctorUserValidationResponse doctorDetails) {
//		System.out.println("Recieved Data" + addressResponse.getName() + "  " + addressResponse.getPassword());
		validation.updateDoctor(doctorDetails);

	}

	@RequestMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	@GetMapping("/doctors/search")
	public ResponseEntity<List<DoctorUserValidationResponse>> searchDoctorsByName(@RequestParam("name") String name) {
		List<DoctorUserValidationResponse> doctors = validation.findDoctorsById(name);
		return ResponseEntity.ok(doctors);
	}

	@GetMapping("/doctors/{name}")
	public ResponseEntity<List<DoctorUserValidation>> getAllAddresses(@PathVariable String name) {
		List<DoctorUserValidation> allDoctors = validation.filterDoctorsByName(name);
		return ResponseEntity.ok(allDoctors);
	}

	@GetMapping("/doctors/filtered")
	public ResponseEntity<List<DoctorUserValidation>> getAllDoctors() {
		List<DoctorUserValidation> allDoctors = validation.filteredDoctors();
		return ResponseEntity.ok(allDoctors);
	}

	@GetMapping("/doctors/requests")
	public ResponseEntity<List<DoctorUserValidation>> getAllDoctorsRequests() {
		List<DoctorUserValidation> allDoctors = validation.doctorRequests();
		return ResponseEntity.ok(allDoctors);
	}

	@GetMapping("/doctors/active")
	public ResponseEntity<List<DoctorUserValidation>> getAllDoctorsActive() {
		List<DoctorUserValidation> allDoctors = validation.doctorsActive();
		return ResponseEntity.ok(allDoctors);
	}
	@PostMapping("/changePassword/{username}/{newPassword}")
	public ResponseEntity<String> changePassword(@PathVariable String username,
			@PathVariable String newPassword) {
//		System.out.println(username);
//		System.out.println(newPassword);
	    boolean isChanged = validation.changePassword(username, newPassword);
	    if (isChanged) {
	        return ResponseEntity.ok("Password changed successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to change password");
	    }
	}
	@DeleteMapping("/delete/{id}")
	public void getUserByUsername(@PathVariable int id) {
		DoctorUserValidationResponse addressResponse=validation.deleteDoctor(id);
	}



}
