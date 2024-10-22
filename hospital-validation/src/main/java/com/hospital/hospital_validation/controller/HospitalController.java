package com.hospital.hospital_validation.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.hospital_validation.response.AppointmentResponse;
import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.response.MessageResponse;
import com.hospital.hospital_validation.response.PatientResponse;
import com.hospital.hospital_validation.security.CustomUserDetails;
import com.hospital.hospital_validation.service.AppointmentService;
import com.hospital.hospital_validation.service.HospitalDoctorService;
import com.hospital.hospital_validation.service.MessageService;

@Controller
public class HospitalController {

	@Autowired
	private HospitalDoctorService docService;
	@Autowired
	private AppointmentService appService;
	

	@RequestMapping("/")
	public String homePage() {
		return "home";
	}

	@RequestMapping("/loginSign")
	public ModelAndView showLoginPage() {
		return new ModelAndView("login");
	}
	@RequestMapping("/index")
	public String home(Principal principal, Model model) {
//		model.addAttribute("name", principal.getName());
		return "home";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied(Principal principal,Model model) {
		String username=principal.getName();
		model.addAttribute("name",username);
		return "accessDenied";
	}
	@RequestMapping("/changePassword")
	public String changePassword(Principal principal, Model model) {
		String username = principal.getName();
		model.addAttribute("username",username);
		return "changePassword";
	}
	
	@PostMapping("/changePasswordProcess")
	public String changePasswordProcess(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String newPassword, Model model,
			Principal principal) {
		if(docService.changePassword(username,password,newPassword)) {
			model.addAttribute("username", principal.getName());
			model.addAttribute("success", "Password Changed Successfully...!");
			return "changePassword";
		}
		model.addAttribute("username", principal.getName());
		model.addAttribute("fail", "Incorrect Current Password");
		return "changePassword";
	}

	@RequestMapping("/showDoctorProfile")
	public ModelAndView shoDoctorProfile(@RequestParam("id") int id) {
//		System.out.println(id);
		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
		List<DoctorUserValidationResponse> doctorDetail = Collections.singletonList(addressResponse);
		ModelAndView model = new ModelAndView();
		model.setViewName("showDoctorProfilePage");
		model.addObject("doctor", doctorDetail);
		return model;
	}
	@GetMapping("/activeDoctors")
	public ModelAndView activeDoctors() {
		ModelAndView model = new ModelAndView();
		model.setViewName("ShowDoctors");
		List<DoctorUserValidationResponse> emp = docService.activeDoctors();
		if (emp.isEmpty()) {
			model.addObject("message", "No Doctor Found Active");
			return model;
		}
		model.addObject("doctors", emp);
		return model;

	}
	@GetMapping("/searchDoctor")
	public ModelAndView getAllEmployees(@RequestParam("id") String name) {
		ModelAndView model = new ModelAndView();
		model.setViewName("ShowDoctors");
		if (name != "") {
			List<DoctorUserValidationResponse> emp = docService.searchDoctor(name);
			if (emp.isEmpty()) {
				model.addObject("message", "No Doctor Found with that Name");
				return model;
			}
			model.addObject("doctors", emp);
			return model;
		} else {
			model.addObject("message", "You are sending No Value , Please Enter Valid Pattern");
			return model;
		}
	}
	@GetMapping("/completeDoctors")
	public ModelAndView completeDoctors() {
		ModelAndView model = new ModelAndView();
		model.setViewName("ShowDoctors");
			List<DoctorUserValidationResponse> emp = docService.completeDoctors();
			if (emp.isEmpty()) {
				model.addObject("message", "No Doctor Found with that Name");
				return model;
			}
			model.addObject("doctors", emp);
			return model;
		
		}


	

}


