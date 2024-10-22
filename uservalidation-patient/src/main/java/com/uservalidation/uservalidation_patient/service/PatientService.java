package com.uservalidation.uservalidation_patient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uservalidation.uservalidation_patient.response.PatientResponse;

@Service
public interface PatientService {
	public PatientResponse findAddressByEmployeeId(int id);
	public PatientResponse addPatient(PatientResponse addPatient);
	public PatientResponse findUser(String username);
	public boolean changePassword(String username, String newPassword);
	public void saveImage(MultipartFile file, String name) throws Exception;
	public byte[] getImage(String username);

}
