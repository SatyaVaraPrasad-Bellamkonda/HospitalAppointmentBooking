package com.uservalidation.uservalidation_hospital.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.uservalidation.uservalidation_hospital.entity.DoctorUserValidation;
import com.uservalidation.uservalidation_hospital.repo.DoctorValidationRepo;
import com.uservalidation.uservalidation_hospital.response.DoctorUserValidationResponse;

@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	private DoctorValidationRepo doctorRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public DoctorUserValidationResponse findAddressByEmployeeId(int id) {
		Optional<DoctorUserValidation> address = doctorRepo.findById(id);
		DoctorUserValidationResponse addressResponse = modelMapper.map(address, DoctorUserValidationResponse.class);
		return addressResponse;
	}

	@Override
	public DoctorUserValidationResponse addDoctor(DoctorUserValidationResponse addressResponse) {
		DoctorUserValidation address = modelMapper.map(addressResponse, DoctorUserValidation.class);
//		address.setLocked(true);
		if (address.getAuthority() == null) {
			address.setLocked(true);
		}
		// Save to the repository
		DoctorUserValidation savedAddress = doctorRepo.save(address);

		// Map back to AddressResponse to return
		DoctorUserValidationResponse map = modelMapper.map(savedAddress, DoctorUserValidationResponse.class);
		return map;
	}

	@Override
	public void updateDoctor(DoctorUserValidationResponse doc) {
		String updateQuery = "UPDATE user_validation SET name=?,degree=?,experience=?,specialist=?,mobile=?,mail=?,locked=?,authority=? WHERE id = ?";
		Object[] args = { doc.getName(), doc.getDegree(), doc.getExperience(), doc.getSpecialist(), doc.getMobile(),
				doc.getMail(), doc.isLocked(), doc.getAuthority(), doc.getId() };
		jdbcTemplate.update(updateQuery, args);

	}

	@Override
	public List<DoctorUserValidationResponse> findDoctorsById(String id) {
//		  return doctorRepository.findByNameContainingIgnoreCase(namePattern);

		return doctorRepo.findByNameLike(id);
//		Optional<DoctorUserValidation> address = doctorRepo.findById(id);
//		DoctorUserValidationResponse addressResponse = modelMapper.map(address, DoctorUserValidationResponse.class);
//		return addressResponse;
	}

	@Override
	public List<DoctorUserValidation> findAllDoctors() {
		return doctorRepo.findAll();
	}

	@Override
	public List<DoctorUserValidation> filteredDoctors() {
		List<DoctorUserValidation> allDoctors = findAllDoctors();
		List<DoctorUserValidation> filteredDoctors = new ArrayList<>();
		for (DoctorUserValidation doctor : allDoctors) {
			// Create a new DoctorUserValidation object without username and password
			DoctorUserValidation filteredDoctor = new DoctorUserValidation();
			filteredDoctor.setId(doctor.getId());
			filteredDoctor.setName(doctor.getName());
			filteredDoctor.setDegree(doctor.getDegree());
			filteredDoctor.setExperience(doctor.getExperience());
			filteredDoctor.setSpecialist(doctor.getSpecialist());
			filteredDoctor.setMobile(doctor.getMobile());
			filteredDoctor.setMail(doctor.getMail());

			filteredDoctors.add(filteredDoctor);
		}

		return filteredDoctors;

	}

	@Override
	public List<DoctorUserValidation> filterDoctorsByName(String name) {
		List<DoctorUserValidation> allDoctors = findAllDoctors();
		List<DoctorUserValidation> filteredDoctors = new ArrayList<>();
		String res = name.toLowerCase();
		for (DoctorUserValidation doctor : allDoctors) {
			if (doctor.getName() != null && doctor.getName().toLowerCase().contains(res)) {
				filteredDoctors.add(doctor);
			}
		}
		return filteredDoctors;

	}

	@Override
	public DoctorUserValidationResponse findUser(String username) {
		DoctorUserValidation data = doctorRepo.findByMail(username);
		DoctorUserValidationResponse doctorDetail = modelMapper.map(data, DoctorUserValidationResponse.class);
		return doctorDetail;
	}

	@Override
	public boolean changePassword(String username, String newPassword) {
		try {
			String sql = "Update user_validation set password=? where mail=?";
			Object[] args = { newPassword, username };
			jdbcTemplate.update(sql, args);
			return true;
		} catch (Exception E) {
			return false;
		}
	}

	public List<DoctorUserValidation> doctorRequests() {

		List<DoctorUserValidation> allDoctors = findAllDoctors();
		List<DoctorUserValidation> filteredDoctors = new ArrayList<>();
		List<DoctorUserValidation> activeDoctors = allDoctors.stream().filter(response -> response.isLocked()) // Keep
																												// only
																												// those
																												// not
																												// locked
				.collect(Collectors.toList());
		for (DoctorUserValidation doctor : activeDoctors) {
			// Create a new DoctorUserValidation object without username and password
			DoctorUserValidation filteredDoctor = new DoctorUserValidation();
			filteredDoctor.setId(doctor.getId());
			filteredDoctor.setName(doctor.getName());
			filteredDoctor.setDegree(doctor.getDegree());
			filteredDoctor.setExperience(doctor.getExperience());
			filteredDoctor.setSpecialist(doctor.getSpecialist());
			filteredDoctor.setMobile(doctor.getMobile());
			filteredDoctor.setMail(doctor.getMail());
			filteredDoctor.setLocked(doctor.isLocked());
			filteredDoctor.setAuthority(doctor.getAuthority());
//			if(filteredDoctor.getAuthority()==null||filteredDoctor.getAuthority()=="user") {
			filteredDoctors.add(filteredDoctor);
//			}
		}

		return filteredDoctors;
	}

	public List<DoctorUserValidation> doctorsActive() {
		List<DoctorUserValidation> allDoctors = findAllDoctors();
		List<DoctorUserValidation> filteredDoctors = new ArrayList<>();
		List<DoctorUserValidation> activeDoctors = allDoctors.stream().filter(response -> !response.isLocked()) // Keep
																												// only
																												// those
																												// not
																												// locked
				.collect(Collectors.toList());
		for (DoctorUserValidation doctor : activeDoctors) {
			// Create a new DoctorUserValidation object without username and password
			DoctorUserValidation filteredDoctor = new DoctorUserValidation();
			filteredDoctor.setId(doctor.getId());
			filteredDoctor.setName(doctor.getName());
			filteredDoctor.setDegree(doctor.getDegree());
			filteredDoctor.setExperience(doctor.getExperience());
			filteredDoctor.setSpecialist(doctor.getSpecialist());
			filteredDoctor.setMobile(doctor.getMobile());
			filteredDoctor.setMail(doctor.getMail());

			filteredDoctors.add(filteredDoctor);
		}

		return filteredDoctors;
	}

	public DoctorUserValidationResponse deleteDoctor(int id) {
		doctorRepo.deleteById(id);
		return null;
	}

}
