package com.hospital.hospital_validation.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.hospital_validation.response.DoctorUserValidationResponse;
import com.hospital.hospital_validation.response.MessageResponse;
import com.hospital.hospital_validation.response.PatientResponse;
import com.hospital.hospital_validation.service.HospitalDoctorService;
import com.hospital.hospital_validation.service.MessageService;

@Controller
public class MessageConnectController {
	@Autowired
	private MessageService mesService;
	@Autowired
	private HospitalDoctorService docService;

	

	@RequestMapping("/doctorChat")
	public ModelAndView messageToDoctor(Principal principal, @RequestParam("mail") String mail) {
		DoctorUserValidationResponse doc = docService.findDoctorByUsername(mail);
		List<MessageResponse> response=mesService.getMessages(principal.getName(),mail);
//		System.out.println(addressResponse.getName());s
		ModelAndView model = new ModelAndView();
		model.setViewName("message");
		model.addObject("messages", response);
		model.addObject("name", doc.getName());
		model.addObject("docId",doc.getId());
		model.addObject("receiver", mail);
		model.addObject("sender", principal.getName());
		return model;
	}
	
	@RequestMapping("/patientChat")
	public ModelAndView messageToPatient(Principal principal, @RequestParam("patient_name") String name) {
//		DoctorUserValidationResponse addressResponse = docService.getByDoctorId(id);
		PatientResponse emp = docService.findPatientByUsername(name);
		List<MessageResponse> response=mesService.getMessages(principal.getName(),emp.getPatient_username());
//		System.out.println(addressResponse.getName());
		ModelAndView model = new ModelAndView();
		model.setViewName("message");
		model.addObject("messages", response);
		model.addObject("name", emp.getPatient_name());
		model.addObject("sender", principal.getName());
		model.addObject("receiver", emp.getPatient_username());
		return model;
	}

	

	
	@PostMapping("/sendMessage")
	public ModelAndView sendMessage(@ModelAttribute MessageResponse message,@RequestParam int docId) {
		mesService.sendMessage(message);

		if(docId!=0) {
        return new ModelAndView("redirect:/doctorChat?mail=" +message.getReceiver() );
		}else {
			return new ModelAndView("redirect:/patientChat?patient_name="+message.getReceiver());
		}

//	    return new ModelAndView("redirect:/doctorChat");
	
	}
	
	@RequestMapping("/viewMessages")
	public ModelAndView viewMessages(Principal principal) {
		ModelAndView model = new ModelAndView();
		List<String> messageList=mesService.showMessages(principal.getName());
		model.setViewName("messageList");
		model.addObject("receiver", principal.getName());
		model.addObject("messages", messageList);
		return model;
	}
	
	@PostMapping("/showMessages")
	public ModelAndView ShowMessages(@RequestParam String sender, @RequestParam String receiver) {
		if(sender.contains("@")) {
			return new ModelAndView("redirect:/doctorChat?mail=" +sender );
		}else {
			
			return new ModelAndView("redirect:/patientChat?patient_name="+sender);
		}
	}
}
