package com.chatbox.scalesampark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatbox.scalesampark.entity.message.MessageType;

@Repository
public interface MessageTypeRepository extends JpaRepository<MessageType, Integer> {

	MessageType findByMessageType(String messageType);

}