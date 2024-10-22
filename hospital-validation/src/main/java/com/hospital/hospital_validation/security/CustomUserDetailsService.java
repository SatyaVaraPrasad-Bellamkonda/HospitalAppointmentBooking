package com.hospital.hospital_validation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.response.PatientResponse;
import com.hospital.hospital_validation.service.HospitalDoctorService;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private HospitalDoctorService docService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	try {
    	DoctorUserValidationResponse doctor = docService.findDoctorByUsername(username);
        if (doctor.getMail()!=null) {
            return new CustomUserDetails(
                doctor.getMail(),
                doctor.getPassword(),
                doctor.getAuthority(),
                doctor.isLocked());
        }}catch(Exception E) {
        	
        }
    	try {
        PatientResponse patient = docService.findPatientByUsername(username);
        if (patient.getPatient_name()!=null) {
            return new CustomUserDetails(
					patient.getPatient_username(),
                patient.getPatient_password()
            );
        }}catch(Exception E) {
        	
        }

        throw new UsernameNotFoundException("User not found");
    }
}
