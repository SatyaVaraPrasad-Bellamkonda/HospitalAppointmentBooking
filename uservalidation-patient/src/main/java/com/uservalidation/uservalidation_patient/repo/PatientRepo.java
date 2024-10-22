package com.uservalidation.uservalidation_patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uservalidation.uservalidation_patient.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

	Patient findByName(String username); 


//	@Query("SELECT p FROM Patient p WHERE p.name = :name")
//    Patient findByName(@Param("name") String name);

}
