package com.hospital.hospital_validation.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.hospital_validation.response.AppointmentResponse;

@Service
public class AppointmentService {
	@Autowired
	private RestTemplate restTemplate;

	public String postAppointment(AppointmentResponse appointment) {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		  System.out.println(appointment.getDate());
		  LocalDate appointmentDate = LocalDate.parse(appointment.getDate(), formatter);
          LocalDate currentDate = LocalDate.now();
         if(appointmentDate.isBefore(currentDate)) {
        	 return "invalidDate";
         }
		try {
			String url = "http://localhost:8084/appointment/addAppointment";
			 ResponseEntity<String> response=restTemplate.postForEntity(url, appointment, String.class);
			 System.out.println(response.getBody());
			 if(response.getBody().equals("success")) {
				 return "success";
			 }else {
				 return "noAvail";
			 }
			 
		} catch (Exception E) {
			return "invalidDate";
		}
	}

	public List<AppointmentResponse> getAppointments(String name) {
//		AppointmentResponse response=new AppointmentResponse();
		if (name.contains("@")) {
		String url = "http://localhost:8084/appointment/doctor/" + name;
        ResponseEntity<List<AppointmentResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AppointmentResponse>>() {});
        return response.getBody();
	} else {
		String url = "http://localhost:8084/appointment/patient/" + name;
		ResponseEntity<List<AppointmentResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AppointmentResponse>>() {
				});
		return response.getBody();
	}

	}
	public List<AppointmentResponse> getAllAppointments() {
//		AppointmentResponse response=new AppointmentResponse();
		String url = "http://localhost:8084/appointment/allAppointments";
        ResponseEntity<List<AppointmentResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AppointmentResponse>>() {});
        return response.getBody();
	
	}

	public Boolean updateAppointment(int id, int status) {
		try {
			String url = "http://localhost:8084/appointment/update/" + id + "/" + status;
			restTemplate.put(url, id, Integer.class);
			return true;
		} catch (Exception E) {
//			E.printStackTrace();
			return false;
		}

	}

	public void saveImage(MultipartFile file, String name) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		byte[] bytes = file.getBytes();
		ByteArrayResource byteArrayResource = new ByteArrayResource(bytes) {
			@Override
			public String getFilename() {
				return file.getOriginalFilename();
			}
		};
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", byteArrayResource);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		restTemplate.postForObject("http://localhost:8083/patient/upload/" + name, requestEntity,
				String.class);
	}

	public String extracted(String username) {
		try {
		String imageUrl = "http://localhost:8083/patient/image/" + username;

		byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);
		if (imageBytes != null) {
			String imageString = Base64.getEncoder().encodeToString(imageBytes);

			return imageString;
		}
	} catch (Exception E) {
		return "failed";
	}
	return "failed";
	}


}
