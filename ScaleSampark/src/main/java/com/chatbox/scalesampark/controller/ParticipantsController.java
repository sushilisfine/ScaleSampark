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
public class ParticipantsController {

	private RegistrationService registerationService;

	public ParticipantsController(RegistrationService registerationService) {

		this.registerationService = registerationService;
	}

	@PostMapping(value = "/v1/participants")
	public ResponseEntity<Object> save(@Valid @RequestBody RegistrationRequestDTO userDetails) {

		User registeredUser = registerationService.register(userDetails);

		Map<Object, Object> response = new HashMap<>();

		if (registeredUser != null) {
			response.put("participant_uuid", registeredUser.getUUID());
			return ResponseEntity.accepted().body(response);
		} else {
			response.put("Error", "Error registering user");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping(value = "/v1/participants/{UUID}")
	public ResponseEntity<Object> delete(@PathVariable String UUID) {

		boolean removed = registerationService.removeParticipant(UUID);

		Map<Object, Object> response = new HashMap<>();
		if (removed)
			response.put("Success", "Successfully removed user : " + UUID);
		else
			response.put("Error", "No user present with uuid : " + UUID);

		return ResponseEntity.accepted().body(response);
	}

	@GetMapping(value = "/v1/participants/{UUID}", produces = "application/json")
	public ResponseEntity<Object> get(@PathVariable String UUID) {

		User user = registerationService.getParticipant(UUID);

		Map<Object, Object> response = new HashMap<>();
		if (user != null)
			return ResponseEntity.accepted().body(user);
		else {
			response.put("Error", "No user with given UUID found");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping(value = "/v1/participants", produces = "application/json")
	public ResponseEntity<Object> getAll() {

		ParticipantsResponseDTO participantsResponse = registerationService.getAllParticipants();

		Map<Object, Object> response = new HashMap<>();
		if (participantsResponse != null)
			return ResponseEntity.accepted().body(participantsResponse);
		else {
			response.put("Error", "No user with given UUID found");
			return ResponseEntity.badRequest().body(response);
		}

	}

}
