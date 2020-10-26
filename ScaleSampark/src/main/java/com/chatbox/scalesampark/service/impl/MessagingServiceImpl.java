package com.chatbox.scalesampark.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
		UUID uuid = UUID.randomUUID();
		if (user == null) {
			// throw custom exception
			return false;
		}
		userHelper.updateLastSeen(messageDTO.getParticipantUUID());

		MessageType messageType = messageHelper.createMessageTypeIfNotExist(messageDTO.getMessageType());

		String encryptedText = messageHelper.encryptMessage(messageDTO.getMessage());

		Message message = Message.builder().messageType(messageType).message(encryptedText).user(user)
				.UUID(uuid.toString()).createdAt(LocalDateTime.now()).build();

		userHelper.updateLastSeen(user.getUUID());
		messageRepository.save(message);
		return true;
	}

	@Override
	public PendingMessageResponseDTO readPendingMessages(String UUID) {

		User user = userHelper.getUserByUUID(UUID);

		if (user == null) {
			// throw custom exception
			return null;
		}

		List<Message> pendingMessages = messageRepository.findByCreatedAtAfter(user.getLastSeen());
		userHelper.updateLastSeen(UUID);

		List<MessageDTO> messageDTO = messageHelper.decryptMessages(pendingMessages);

		PendingMessageResponseDTO pendingMessageResponseDTO = new PendingMessageResponseDTO();
		pendingMessageResponseDTO.setPendingMessage(messageDTO);

		return pendingMessageResponseDTO;
	}

}
