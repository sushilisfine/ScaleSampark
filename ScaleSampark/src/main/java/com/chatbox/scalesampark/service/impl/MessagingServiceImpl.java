package com.chatbox.scalesampark.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatbox.scalesampark.dto.request.MessageDTO;
import com.chatbox.scalesampark.dto.request.NewMessageRequestDTO;
import com.chatbox.scalesampark.dto.response.PendingMessageResponseDTO;
import com.chatbox.scalesampark.entity.message.Message;
import com.chatbox.scalesampark.entity.message.MessageType;
import com.chatbox.scalesampark.entity.user.User;
import com.chatbox.scalesampark.repository.MessageRepository;
import com.chatbox.scalesampark.service.MessagingService;
import com.chatbox.scalesampark.service.utility.MessageHelper;
import com.chatbox.scalesampark.service.utility.UserHelper;

@Service
public class MessagingServiceImpl implements MessagingService {

	private MessageRepository messageRepository;
	private UserHelper userHelper;
	private MessageHelper messageHelper;

	public MessagingServiceImpl(MessageRepository messageRepository, UserHelper userHelper,
			MessageHelper messageHelper) {

		this.messageHelper = messageHelper;
		this.messageRepository = messageRepository;
		this.userHelper = userHelper;
	}

	@Override
	public boolean sendMessage(NewMessageRequestDTO messageDTO) {

		User user = userHelper.getUserByUUID(messageDTO.getParticipantUUID());

		if (user == null) {
			// throw custom exception
			return false;
		}
		userHelper.updateLastSeen(messageDTO.getParticipantUUID());

		MessageType messageType = messageHelper.createMessageTypeIfNotExist(messageDTO.getMessageType());

		String text = messageDTO.getMessage();

		String encryptedText = messageHelper.encryptMessage(text);

		userHelper.updateLastSeen(user.getUuid());
		Message message = Message.builder().messageType(messageType).message(encryptedText).pending(true).user(user)
				.build();
		messageRepository.save(message);
		return true;
	}

	@Override
	public PendingMessageResponseDTO readPendingMessages(Integer uuid) {

		User user = userHelper.getUserByUUID(uuid);

		if (user == null) {
			// throw custom exception
			return null;
		}

		userHelper.updateLastSeen(uuid);

		List<Message> pendingMessages = messageRepository.findAllByPending(true);

		messageHelper.setMessageAsRead(pendingMessages);

		List<MessageDTO> messageDTO = messageHelper.decryptMessages(pendingMessages);

		PendingMessageResponseDTO pendingMessageResponseDTO = new PendingMessageResponseDTO();
		pendingMessageResponseDTO.setPendingMessage(messageDTO);

		return pendingMessageResponseDTO;
	}

}
