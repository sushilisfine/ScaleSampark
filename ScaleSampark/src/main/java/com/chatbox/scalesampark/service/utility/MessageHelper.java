package com.chatbox.scalesampark.service.utility;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.chatbox.scalesampark.dto.request.MessageDTO;
import com.chatbox.scalesampark.entity.message.Message;
import com.chatbox.scalesampark.entity.message.MessageType;
import com.chatbox.scalesampark.entity.user.User;
import com.chatbox.scalesampark.repository.MessageRepository;
import com.chatbox.scalesampark.repository.MessageTypeRepository;

@Component
@Transactional
public class MessageHelper {

	private MessageRepository messageRepository;
	private MessageTypeRepository messageTypeRepository;
	private AESEncryptionDecryption encDec;

	public MessageHelper(MessageRepository messageRepository, MessageTypeRepository messageTypeRepository,
			AESEncryptionDecryption encDec) {

		this.encDec = encDec;
		this.messageRepository = messageRepository;
		this.messageTypeRepository = messageTypeRepository;
	}

	public void setMessageAsRead(List<Message> pendingMessages) {

		pendingMessages.forEach(message -> {
			message.setPending(false);
			messageRepository.save(message);
		});
	}

	public MessageType createMessageTypeIfNotExist(Integer messageType) {

		MessageType messageTypeDb = messageTypeRepository.findByMessageType(messageType);
		if (messageTypeDb == null) {
			messageTypeDb = new MessageType();
			messageTypeDb.setMessageType(messageType);
			messageTypeDb = messageTypeRepository.save(messageTypeDb);
		}
		return messageTypeDb;
	}

	public List<MessageDTO> decryptMessages(List<Message> pendingMessages) {

		List<MessageDTO> messageDTO = pendingMessages.stream().map(message -> {

			return MessageDTO.builder().message(encDec.decrypt(message.getMessage()))
					.messageType(message.getMessageType().getMessageType()).participantUUID(message.getUser().getUuid())
					.uuid(message.getUuid()).build();

		}).collect(Collectors.toList());

		return messageDTO;
	}

	public String encryptMessage(String strToEncrypt) {
		return encDec.encrypt(strToEncrypt);
	}

	public void removeMessagesForUser(User user) {

		messageRepository.deleteAllByUser(user);

	}
}
