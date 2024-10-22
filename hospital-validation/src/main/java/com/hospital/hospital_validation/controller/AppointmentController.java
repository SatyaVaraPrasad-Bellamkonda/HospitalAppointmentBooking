package com.hospital.hospital_validation.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.hospital_validation.response.AppointmentResponse;
import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.security.CustomUserDetails;
import com.hospital.hospital_validation.service.AppointmentService;
import com.hospital.hospital_validation.service.HospitalDoctorService;

@Controller
public class AppointmentController {
	@Autowired
	private AppointmentService appService;
	@Autowired
	private HospitalDoctorService docService;
	
	
	
	@RequestMapping("/bookDoctorAppointment")
	public ModelAndView app(Principal principal, @RequestParam("id") int id) {
		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
//		System.out.println(addressResponse.getName());
		ModelAndView model = new ModelAndView();
		model.setViewName("appointment");
		model.addObject("doctorname", addressResponse.getName());
		model.addObject("doctormail", addressResponse.getMail());
		model.addObject("name", principal.getName());
		return model;
	}

	@RequestMapping("/dashboard")
	public ModelAndView dash(Principal principal,@AuthenticationPrincipal CustomUserDetails prin) {
		List<AppointmentResponse> response = appService.getAppointments(principal.getName());
		List<AppointmentResponse> allData = appService.getAllAppointments();
//		System.out.println(response.getDoctormail());
//		for(AppointmentResponse res:response) {
//			System.out.println(res.getPatient_name());
//		}
		ModelAndView model = new ModelAndView();
		
		if(prin.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
			model.setViewName("dashboard");
			model.addObject("doctor", "show");
			model.addObject("appointments", allData);
			return model;
		}
		if (principal.getName().contains("@")) {
			model.setViewName("dashboard");
			model.addObject("appointments", response);
			return model;
		} else {
			model.setViewName("patientDashboard");
			model.addObject("appointments", response);
			return model;
		}
	}

	

	

	@RequestMapping("/image")
	public String image() {
		return "image";
	}

	@PostMapping("/upload")
	public ModelAndView uploadImage(@RequestParam("file") MultipartFile file, Principal principal) {
		ModelAndView model = new ModelAndView();
		model.setViewName("image");
		if (file.getOriginalFilename().endsWith(".jpeg") || file.getOriginalFilename().endsWith(".jpg")) {
		try {
			appService.saveImage(file, principal.getName());
			model.addObject("success", "Image Uploaded Successfully..!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("fail", "Image Not Uploaded");
		}
	} else {
		model.addObject("unsupport", "Image must be jpg or jpeg");
	}
		return model;
	}

	@GetMapping("/viewRecords")
	public String viewImage(Principal principal, Model model) {
		String imageBytes = appService.extracted(principal.getName());

		if (imageBytes != "failed") {
			model.addAttribute("image", imageBytes);
			model.addAttribute("imageType", "image/jpeg");
		} else {
			model.addAttribute("error", "Image not found");
		}

		return "imageView"; // Replace with your actual JSP file name
	}

	@GetMapping("/showAppointmentById")
	public String showAppointment(@RequestParam("patient_name") String name, Model model) {
		String imageBytes = appService.extracted(name);

		if (imageBytes != "failed") {
			model.addAttribute("image", imageBytes);
			model.addAttribute("imageType", "image/jpeg");
		} else {
			model.addAttribute("error", "Image not found");
		}

		return "imageView"; // Replace with your actual JSP file name
	}
	@PostMapping("/updateAppointment")
	public ModelAndView appointmentSubmit(Principal principal,@ModelAttribute AppointmentResponse appointment) {
		ModelAndView model = new ModelAndView();
		model.setViewName("appointment");
		model.addObject("doctorname", appointment.getDoctormail());
		model.addObject("doctormail", appointment.getDoctormail());
		model.addObject("name", principal.getName());
		String appStatus=appService.postAppointment(appointment);
		if (appStatus=="success") {
			model.addObject("success", "Your Appointment Booked Successfully...!");
			model.addObject("dash", "Go to Dashboard to view appointment status.");
		}else if(appStatus=="noAvail") {
			model.addObject("fail", "Appointments Filled on that date");
		}
		
		else {
			model.addObject("fail", "The date You Have Selected is not valid");
		}
		return model;
//		System.out.println(appointment.getDate());
	}
	@PostMapping("/withdrawPatient")
	public ModelAndView withdrawAppointment(Principal principal, @RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		if (appService.updateAppointment(id,3)) {
			List<AppointmentResponse> response = appService.getAppointments(principal.getName());
//			System.out.println(response.getDoctormail());
//			for(AppointmentResponse res:response) {
//				System.out.println(res.getPatient_name());
//			}
			model.setViewName("patientDashboard");
			model.addObject("appointments", response);
			model.addObject("data", "d");
			return model;

		} else {
			model.setViewName("errorSignUp");
			model.addObject("name", "error");
			return model;
		}
	}
}
