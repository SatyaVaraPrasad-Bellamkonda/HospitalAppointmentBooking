package com.hospital.hospital_validation.service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.hospital.hospital_validation.response.MessageResponse;

@Service
public class MessageService {
	@Autowired
	private RestTemplate restTemplate;


	public void sendMessage(MessageResponse message) {
		message.setTimestamp(LocalDateTime.now());
		String url = "http://localhost:8084/appointment/message/addMessage";
		restTemplate.postForEntity(url, message, String.class);		
	}
	
	
	

	public List<MessageResponse> getMessages(String name, String patient_username) {
		String url = "http://localhost:8084/appointment/"+name+"/" + patient_username;
		ResponseEntity<List<MessageResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MessageResponse>>() {
				});
		return response.getBody();
	}




	public List<String> showMessages(String name) {
		String url = "http://localhost:8084/appointment/message/"+name;
		ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		return response.getBody();
		
	}

}
