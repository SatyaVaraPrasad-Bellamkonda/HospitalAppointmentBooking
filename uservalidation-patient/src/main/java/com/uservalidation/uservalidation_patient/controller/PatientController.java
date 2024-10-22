package com.uservalidation.uservalidation_patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uservalidation.uservalidation_patient.response.PatientResponse;
import com.uservalidation.uservalidation_patient.service.PatientServiceImpl;

@RestController
public class PatientController {
	@Autowired
	private PatientServiceImpl validation;

	@GetMapping("/user/{id}")
	public ResponseEntity<PatientResponse> getAddressByEmployeeId(@PathVariable int id) {
		PatientResponse addressResponse = validation.findAddressByEmployeeId(id);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
	@GetMapping("/patientValidation/{username}")
	public ResponseEntity<PatientResponse> getUserByUsername(@PathVariable String username) {
		PatientResponse addressResponse=validation.findUser(username);
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}

	@PostMapping("/addFromAnother")
	public void addAddressFromAnother(@RequestBody PatientResponse addressResponse, Model model) {
		System.out.println(
				"Recieved Data" + addressResponse.getPatient_name() + "  " + addressResponse.getPatient_password());
		PatientResponse savedAddress = validation.addPatient(addressResponse);
	}

//	@PostMapping("/changePassword")
//	public ResponseEntity<String> changePassword(@RequestParam String username, @RequestParam String newPassword) {
////		System.out.println(username);
////		System.out.println(newPassword);
//		boolean isChanged = validation.changePassword(username, newPassword);
//		if (isChanged) {
//			return ResponseEntity.ok("Password changed successfully");
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to change password");
//		}
//	}
	@PostMapping("/changePassword/{username}/{newPassword}")
	public ResponseEntity<String> changePassword(@PathVariable String username, @PathVariable String newPassword) {
//		System.out.println(username);
//		System.out.println(newPassword);
		boolean isChanged = validation.changePassword(username, newPassword);
		if (isChanged) {
			return ResponseEntity.ok("Password changed successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to change password");
		}
	}

	@PostMapping("/upload/{patientname}")
	public void uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String patientname) {
		try {
			validation.saveImage(file, patientname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/image/{username}")
	public ResponseEntity<byte[]> getImage(@PathVariable String username) {
		byte[] imageBytes = validation.getImage(username);

		if (imageBytes == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}

//	@GetMapping("/patient/{name}")
//	public ResponseEntity<PatientResponse> getDetailsByPatientName(@PathVariable String name) {
//		PatientResponse addressResponse = validation.findDetailsByPatientName(name);
//		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
//	}

}
