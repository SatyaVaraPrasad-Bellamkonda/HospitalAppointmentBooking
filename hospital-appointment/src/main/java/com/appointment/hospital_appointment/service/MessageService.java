package com.appointment.hospital_appointment.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.appointment.hospital_appointment.entity.Message;
import com.appointment.hospital_appointment.repo.MessageRepo;
import com.appointment.hospital_appointment.response.MessageResponse;

@Service
public class MessageService {

	@Autowired
	private MessageRepo repo;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ModelMapper modelMapper;
	public MessageResponse addMessage(MessageResponse appointment) {
		Message message = modelMapper.map(appointment, Message.class);
		Message savedAddress = repo.save(message);
		MessageResponse map = modelMapper.map(savedAddress, MessageResponse.class);
		return map;
	}
	 public List<Message> getMessages(String sender, String receiver) {
		 
//		 	String url="select * from message where sender=? and receiver=? or sender=? and receiver=?;";
//		 	Object[] op= {sender,receiver,receiver,sender};
//		 	return jdbcTemplate.query(url, op ,new BeanPropertyRowMapper<>(Message.class));
		 
		 
	        return repo.findBySenderAndReceiverOrReceiverAndSender(sender, receiver, sender, receiver);
	    }
	public List<String> getMessageList(String receiver) {
//		String url=" select distinct sender from message where receiver=? ";
//		Object[] obj= {receiver};
		
		
//		return jdbcTemplate.query(url, obj ,new BeanPropertyRowMapper<>(Message.class));
		return repo.findDistinctSendersByReceiver(receiver);

	}

	

}
