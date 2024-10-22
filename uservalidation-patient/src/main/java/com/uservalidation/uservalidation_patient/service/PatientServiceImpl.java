package com.uservalidation.uservalidation_patient.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uservalidation.uservalidation_patient.entity.Patient;
import com.uservalidation.uservalidation_patient.repo.PatientRepo;
import com.uservalidation.uservalidation_patient.response.PatientResponse;

@Service
public class PatientServiceImpl {
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PatientResponse findAddressByEmployeeId(int id) {
		Optional<Patient> address = patientRepo.findById(id);
		PatientResponse addressResponse = modelMapper.map(address, PatientResponse.class);
		return addressResponse;
	}

	public PatientResponse addPatient(PatientResponse addPatient) {
		Patient address = modelMapper.map(addPatient, Patient.class);

		// Save to the repository
		Patient savedAddress = patientRepo.save(address);

		// Map back to AddressResponse to return
		PatientResponse map = modelMapper.map(savedAddress, PatientResponse.class);
		return map;
	}

	public PatientResponse findUser(String username) {
		Patient data=patientRepo.findByName(username);
		PatientResponse doctorDetail=modelMapper.map(data,PatientResponse.class);
		return doctorDetail;
	}

	public boolean changePassword(String username, String newPassword) {
		try {
			String sql = "Update patient set patient_password=? where patient_username=?";
			Object[] args = { newPassword, username };
			jdbcTemplate.update(sql, args);
			return true;
		} catch (Exception E) {
//			E.printStackTrace();
			return false;
		}
	}

	public void saveImage(MultipartFile file, String name) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("File is empty");
		}

		try {
			byte[] imageBytes = file.getBytes();

			String sql = "UPDATE patient SET image=? WHERE patient_username=?";
			Object[] args = { imageBytes, name }; 

			jdbcTemplate.update(sql, args);

		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("Error saving image: " + e.getMessage());
		}
	}
	
	public byte[] getImage(String username) {
		String sql = "SELECT image FROM patient WHERE patient_username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, byte[].class);
	}


}
