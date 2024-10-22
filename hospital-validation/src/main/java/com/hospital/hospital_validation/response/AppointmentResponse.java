package com.hospital.hospital_validation.response;

public class AppointmentResponse {
	private int id;
	private String patient_name;
	private String doctormail;
	private String email;
	private String patient_mobile;
	private String patient_place;
	private String service;
	private String date;
	private String duration;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatient_mobile() {
		return patient_mobile;
	}

	public void setPatient_mobile(String patient_mobile) {
		this.patient_mobile = patient_mobile;
	}

	public String getPatient_place() {
		return patient_place;
	}

	public void setPatient_place(String patient_place) {
		this.patient_place = patient_place;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}



	public String getDoctormail() {
		return doctormail;
	}

	public void setDoctormail(String doctormail) {
		this.doctormail = doctormail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
