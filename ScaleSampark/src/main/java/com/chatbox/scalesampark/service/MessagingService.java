package com.chatbox.scalesampark.service;

import com.chatbox.scalesampark.dto.request.NewMessageRequestDTO;
import com.chatbox.scalesampark.dto.response.PendingMessageResponseDTO;

public interface MessagingService {

	boolean sendMessage(NewMessageRequestDTO message);

	PendingMessageResponseDTO readPendingMessages(Integer uuid);
}
