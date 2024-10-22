package com.uservalidation.uservalidation_hospital.service;

import java.util.List;

import com.uservalidation.uservalidation_hospital.entity.DoctorUserValidation;
import com.uservalidation.uservalidation_hospital.response.DoctorUserValidationResponse;

public interface ValidationService {

	public DoctorUserValidationResponse findAddressByEmployeeId(int id);

	public DoctorUserValidationResponse addDoctor(DoctorUserValidationResponse addressResponse);

	public void updateDoctor(DoctorUserValidationResponse doc);

	public List<DoctorUserValidationResponse> findDoctorsById(String id);

	public List<DoctorUserValidation> findAllDoctors();

	public List<DoctorUserValidation> filterDoctorsByName(String name);

	public DoctorUserValidationResponse findUser(String username);

	public boolean changePassword(String username, String newPassword);

	List<DoctorUserValidation> filteredDoctors();

}
