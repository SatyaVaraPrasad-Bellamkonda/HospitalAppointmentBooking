package com.appointment.hospital_appointment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appointment.hospital_appointment.entity.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {

	List<Message> findBySenderAndReceiverOrReceiverAndSender(String sender, String receiver, String receiver2,
			String sender2);

	@Query("SELECT DISTINCT m.sender FROM Message m WHERE m.receiver = ?1")
    List<String> findDistinctSendersByReceiver(String receiver);


}
