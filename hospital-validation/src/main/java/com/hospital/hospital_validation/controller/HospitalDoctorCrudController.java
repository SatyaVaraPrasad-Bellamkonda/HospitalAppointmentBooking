package com.hospital.hospital_validation.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.service.HospitalDoctorService;

@Controller
public class HospitalDoctorCrudController {
	
	@Autowired
	private HospitalDoctorService docService;
	
	@GetMapping("/view/doctors/{id}")
	public ModelAndView showDoctorById(@PathVariable int id) {
		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
		List<DoctorUserValidationResponse> employeeList = Collections.singletonList(addressResponse);
		ModelAndView model = new ModelAndView();
		model.setViewName("ShowDoctors");
		model.addObject("employees", employeeList);
		return model;
	}
	@RequestMapping("/insertDoctor")
	public String insertDoctor() {
		return "insertDoctorPage";
	}

	@RequestMapping("/updateDoctor")
	public ModelAndView updateDoctor() {
		ModelAndView up = new ModelAndView();
		up.setViewName("updateDelete");
		up.addObject("message", "update");
		return up;
	}

	@PostMapping("/updateDoctorStatus")
	public ModelAndView updateDoctor(@RequestParam("id") int id) {
		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
		List<DoctorUserValidationResponse> doctorList = Collections.singletonList(addressResponse);

		if (doctorList.get(0) == null) {
			ModelAndView search = new ModelAndView();
			search.setViewName("editDoctor");
			search.addObject("message", "There is no data with the given ID");
			return search;
		} else {
			return new ModelAndView("editDoctor", "user", doctorList);
		}
	}
	@PostMapping("/doctorDetailsSubmit")
	public ModelAndView doctorSignUp(@ModelAttribute DoctorUserValidationResponse doctor) {
		ModelAndView model = new ModelAndView();
		if(docService.signup(doctor)) {
			model.setViewName("login");
			model.addObject("success", "Successfully Registered....!");
			return model;
		}else {
			model.setViewName("login");
			model.addObject("errorMail", "Error...! Email Already Exist.");
			model.addObject("showDoctor", true);
			return model;
		}
	}
	@PostMapping("/deleteDoctorStatus")
	public ModelAndView deleteDoctor(@RequestParam("id") int id) {
		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
		List<DoctorUserValidationResponse> doctorList = Collections.singletonList(addressResponse);

		ModelAndView search = new ModelAndView();
		if (doctorList.get(0) == null) {
			search.setViewName("updateDelete");
			search.addObject("message", "delete");
			search.addObject("noData", "There is no data with the given ID");
			return search;
		} else {
			docService.deleteDoctorById(id);
			search.setViewName("updateDelete");
			search.addObject("message", "delete");
			search.addObject("success", "Data Deleted Successfull..!");		
			return search;
		}
	}

	@RequestMapping("/deleteDoctor")
	public ModelAndView deleteDoctor() {
		ModelAndView up = new ModelAndView();
		up.setViewName("updateDelete");
		up.addObject("message", "delete");
		return up;
	}
	
	
	
	
	// ADMIN RELATED
	@RequestMapping("/viewDoctorRequests")
	public ModelAndView doctorRequests() {
		ModelAndView model = new ModelAndView();
		model.setViewName("doctorRequests");
		List<DoctorUserValidationResponse> emp = docService.doctorRequests();
		if (emp.isEmpty()) {
			model.addObject("message", "No Requests");
			return model;
		}
		model.addObject("doctors", emp);
		return model;
	}

	@PostMapping("/approveDoctorSignUp")
	public String doctorRequestsApprove(@RequestParam int id) {
		return "redirect:/updateDoctorStatus";
	}
	@PostMapping("/updateDoctorPost")
	public ModelAndView updateDoctorPost(@ModelAttribute DoctorUserValidationResponse doctorData) {
		List<DoctorUserValidationResponse> doctorList = Collections.singletonList(doctorData);
		ModelAndView model = new ModelAndView();
		model.addObject("user", doctorList);
		if (docService.updateDoctor(doctorData)) {
			model.addObject("success", "Successfully Updated....!");
			model.setViewName("editDoctor");
			return model;
		} else {
			model.setViewName("editDoctor");
			model.addObject("errorMail", doctorData.getMail() + " , Already Exists");
			model.addObject("error", " , Mail id Already Exists...!");
			return model;
		}
//		docService.updateDoctor(doctorData);
//		System.out.println("Success");

	}

}
