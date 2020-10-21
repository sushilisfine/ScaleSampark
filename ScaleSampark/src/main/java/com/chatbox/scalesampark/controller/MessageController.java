package com.chatbox.scalesampark.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatbox.scalesampark.dto.request.NewMessageRequestDTO;
import com.chatbox.scalesampark.dto.response.PendingMessageResponseDTO;
import com.chatbox.scalesampark.service.MessagingService;

@RestController
@RequestMapping("api")
public class MessageController {

	private MessagingService messagingService;

	public MessageController(MessagingService messagingService) {
		this.messagingService = messagingService;
	}

	@PostMapping(value = "/v1/message")
	public ResponseEntity<Map<Object, Object>> create(@Valid @RequestBody NewMessageRequestDTO message) {

		boolean saved = messagingService.sendMessage(message);

		Map<Object, Object> response = new HashMap<>();

		if (saved) {
			response.put("Success", "Message sent successfully");
			return ResponseEntity.accepted().body(response);
		} else {
			response.put("error", "User UUID dosen't exist");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping(value = "/v1/message/{uuid}", produces = "application/json")
	public ResponseEntity<Object> get(@PathVariable Integer uuid) {

		PendingMessageResponseDTO pendingMessages = messagingService.readPendingMessages(uuid);
		Map<Object, Object> response = new HashMap<>();

		if (pendingMessages != null) {
			return ResponseEntity.accepted().body(pendingMessages);

		} else {
			response.put("Error", "User for given UUID not found");
			return ResponseEntity.badRequest().body(response);

		}
	}

}
