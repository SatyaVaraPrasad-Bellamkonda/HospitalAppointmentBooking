package com.appointment.hospital_appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.hospital_appointment.entity.Message;
import com.appointment.hospital_appointment.response.MessageResponse;
import com.appointment.hospital_appointment.service.MessageService;

@RestController
public class MessageController {
	@Autowired
	private MessageService message;
	
	
	@PostMapping("/message/addMessage")
	public void addAppointment(@RequestBody MessageResponse messageData, Model model) {
//		System.out.println(
//				"Recieved Data" + addressResponse.getPatient_name() + "  " + addressResponse.getPatient_password());
		MessageResponse messageSaved = message.addMessage(messageData);
	} 
	
	@GetMapping("/{sender}/{receiver}")
    public ResponseEntity<List<Message>> chat(@PathVariable String sender, @PathVariable String receiver, Model model) {
        List<Message> messages = message.getMessages(sender, receiver);
//        model.addAttribute("messages", messages);
//        model.addAttribute("sender", sender);
//        model.addAttribute("receiver", receiver);
        return ResponseEntity.ok(messages);
	}
	
	@GetMapping("/message/{receiver}")
	public ResponseEntity<List<String>> chatList(@PathVariable String receiver, Model model) {
        List<String> messages = message.getMessageList(receiver);
//        model.addAttribute("messages", messages);
//        model.addAttribute("sender", sender);
//        model.addAttribute("receiver", receiver);
        return ResponseEntity.ok(messages);
	}

}
