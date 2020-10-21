package com.chatbox.scalesampark.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatbox.scalesampark.dto.request.RegistrationRequestDTO;
import com.chatbox.scalesampark.dto.response.ParticipantsResponseDTO;
import com.chatbox.scalesampark.entity.user.User;
import com.chatbox.scalesampark.service.RegistrationService;

@RestController
@RequestMapping("api")
public class RegistrationController {

	private RegistrationService registerationService;

	public RegistrationController(RegistrationService registerationService) {

		this.registerationService = registerationService;
	}

	@PostMapping(value = "/v1/register")
	public ResponseEntity<Object> save(@Valid @RequestBody RegistrationRequestDTO userDetails) {

		User registeredUser = registerationService.register(userDetails);

		Map<Object, Object> response = new HashMap<>();

		if (registeredUser != null) {
			response.put("participant uuid", registeredUser.getUuid());
			return ResponseEntity.accepted().body(response);
		} else {
			response.put("error", "Error registering user");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(value = "/v1/deregister/{uuid}")
	public ResponseEntity<Object> delete(@PathVariable Integer uuid) {

		boolean removed = registerationService.removeParticipant(uuid);

		Map<Object, Object> response = new HashMap<>();
		if (removed)
			response.put("Successfully removed user ", uuid);
		else
			response.put("No user present with uuid", uuid);

		return ResponseEntity.accepted().body(response);
	}

	@GetMapping(value = "/v1/participants/{uuid}", produces = "application/json")
	public ResponseEntity<Object> get(@PathVariable Integer uuid) {

		ParticipantsResponseDTO participantsResponse = registerationService.getAllParticipants(uuid);

		Map<Object, Object> response = new HashMap<>();
		if (participantsResponse != null)
			return ResponseEntity.accepted().body(participantsResponse);
		else {
			response.put("error", "No user with given UUID found");
			return ResponseEntity.badRequest().body(response);
		}

	}

}
