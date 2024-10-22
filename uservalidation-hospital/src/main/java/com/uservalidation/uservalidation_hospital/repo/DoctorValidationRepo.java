package com.uservalidation.uservalidation_hospital.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uservalidation.uservalidation_hospital.entity.DoctorUserValidation;
import com.uservalidation.uservalidation_hospital.response.DoctorUserValidationResponse;

public interface DoctorValidationRepo extends JpaRepository<DoctorUserValidation, Integer> {

//	List<DoctorUserValidationResponse> findByNameContainingIgnoreCase(String newId);

	@Query(value = "SELECT a.id, a.name, a.degree, a.experience, a.specialist, a.mobile, a.mail FROM hospital.user_validation a WHERE a.name LIKE '%:name%'", nativeQuery = true)
//	@Query("SELECT * from user_validation WHERE d.name LIKE %:name%")
	List<DoctorUserValidationResponse> findByNameLike(@Param("name") String name);

	DoctorUserValidation findByMail(String username);

}
