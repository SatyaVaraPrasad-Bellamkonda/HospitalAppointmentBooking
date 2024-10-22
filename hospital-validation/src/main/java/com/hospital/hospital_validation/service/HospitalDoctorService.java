package com.hospital.hospital_validation.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.response.PatientResponse;

@Service
public class HospitalDoctorService {
	@Autowired
	private RestTemplate restTemplate;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public DoctorUserValidationResponse getByDoctorId(int id) {

		DoctorUserValidationResponse addressResponse = new DoctorUserValidationResponse();

		addressResponse = restTemplate.getForObject("http://localhost:8080/user/doctor/{id}",
				DoctorUserValidationResponse.class, id);

		return addressResponse;

	}

	public boolean signup(DoctorUserValidationResponse doctorData) {
		String encoded = passwordEncoder.encode(doctorData.getPassword());
		doctorData.setPassword(encoded);

//		System.out.println(employeeDataView.getMobile());
		try {
			String url = "http://localhost:8080/user/addFromAnother";
			restTemplate.postForEntity(url, doctorData, String.class);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public boolean signupPatient(PatientResponse patientData) {
		try {
			String encoded = passwordEncoder.encode(patientData.getPatient_password());
			patientData.setPatient_password(encoded);

			System.out.println(patientData.getPatient_mobile());

			String url = "http://localhost:8083/patient/addFromAnother";
			restTemplate.postForEntity(url, patientData, String.class);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public List<DoctorUserValidationResponse> searchDoctor(String name) {

		String url = "http://localhost:8080/user/doctors/" + name;
		ResponseEntity<List<DoctorUserValidationResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DoctorUserValidationResponse>>() {
				});
		

		return response.getBody();

	}

	public DoctorUserValidationResponse findDoctorByUsername(String username) {
		DoctorUserValidationResponse addressResponse = new DoctorUserValidationResponse();
		addressResponse = restTemplate.getForObject("http://localhost:8080/user/doctorValidation/{username}",
				DoctorUserValidationResponse.class, username);
		// (url of the address with {id}, response storing, {id} value)

		return addressResponse;
	}

	public PatientResponse findPatientByUsername(String username) {
		PatientResponse patientData = new PatientResponse();
		patientData = restTemplate.getForObject("http://localhost:8083/patient/patientValidation/{username}",
				PatientResponse.class, username);
		// (url of the address with {id}, response storing, {id} value)

		return patientData;
	}

	public boolean changePassword(String username, String password, String newPassword) {
		if (username.contains("@")) {
			try {
				String hashPassword = findDoctorByUsername(username).getPassword();
				String newEncoded = passwordEncoder.encode(newPassword);
				if (passwordEncoder.matches(password, hashPassword)) {

//					String url = UriComponentsBuilder.fromUriString("http://localhost:8080/user/changePassword")
//							.queryParam("username", username).queryParam("newPassword", newEncoded).build()
//							.toUriString();
//
//					restTemplate.postForEntity(url, null, String.class);
					restTemplate.postForObject("http://localhost:8080/user/changePassword/" +username+"/"+newEncoded, null,
							String.class);

					// Check response status
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			try {
				String hashPassword = findPatientByUsername(username).getPatient_password();
				String newEncoded = passwordEncoder.encode(newPassword);
				if (passwordEncoder.matches(password, hashPassword)) {

//					String url = UriComponentsBuilder.fromUriString("http://localhost:8083/patient/changePassword")
//							.queryParam("username", username).queryParam("newPassword", newEncoded).build()
//							.toUriString();
//					restTemplate.postForEntity(url, null, String.class);

					restTemplate.postForObject("http://localhost:8083/patient/changePassword/" +username+"/"+newEncoded, null,
							String.class);
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}

	public Boolean updateDoctor(DoctorUserValidationResponse doctorData) {
		try {
			String url = "http://localhost:8080/user/update";
			restTemplate.postForEntity(url, doctorData, String.class);
			return true;
		} catch (Exception E) {
//			E.printStackTrace();
			return false;
		}

	}

	public List<DoctorUserValidationResponse> completeDoctors() {
		String url = "http://localhost:8080/user/doctors/filtered";
		ResponseEntity<List<DoctorUserValidationResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DoctorUserValidationResponse>>() {
				});

		return response.getBody();
	}

	public List<DoctorUserValidationResponse> doctorRequests() {
		String url = "http://localhost:8080/user/doctors/requests";
		ResponseEntity<List<DoctorUserValidationResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DoctorUserValidationResponse>>() {
				});
		// as we are storing the data in the Response Entity by using response.getBody()
		// we are converting that Response Entity into list

		return response.getBody();
	}

	public List<DoctorUserValidationResponse> activeDoctors() {
		String url = "http://localhost:8080/user/doctors/active";
		ResponseEntity<List<DoctorUserValidationResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DoctorUserValidationResponse>>() {
				});

		return response.getBody();
	}

	public void deleteDoctorById(int id) {
		String url = "http://localhost:8080/user/delete/" + id ;
		restTemplate.delete(url, id, Integer.class);		
	}

}
