package com.chatbox.scalesampark.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chatbox.scalesampark.dto.request.RegistrationRequestDTO;
import com.chatbox.scalesampark.dto.response.ParticipantsResponseDTO;
import com.chatbox.scalesampark.entity.user.User;
import com.chatbox.scalesampark.repository.UserRepository;
import com.chatbox.scalesampark.service.RegistrationService;
import com.chatbox.scalesampark.service.utility.UserHelper;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private UserRepository userRepository;
	private UserHelper userHelper;

	public RegistrationServiceImpl(UserRepository userRepository, UserHelper userHelper) {

		this.userHelper = userHelper;
		this.userRepository = userRepository;
	}

	@Override
	public User register(RegistrationRequestDTO registerationDTO) {

		UUID uuid = UUID.randomUUID();

		User user = User.builder().email(registerationDTO.getEmail()).lastSeen(LocalDateTime.now())
				.nickname(registerationDTO.getNickname()).UUID(uuid.toString()).build();

		User registeredUser = userRepository.save(user);

		return registeredUser;
	}

	@Override
	public ParticipantsResponseDTO getAllParticipants() {

		ParticipantsResponseDTO participants = new ParticipantsResponseDTO();
		List<User> users = userRepository.findAll();
		participants.setParticipants(users);

		return participants;
	}

	@Override
	public boolean removeParticipant(String UUID) {

		boolean exists = userRepository.existsByUUID(UUID);
		if (exists) {
			userHelper.removeUser(UUID);
			return true;
		}
		return false;
	}

	@Override
	public User getParticipant(String UUID) {
		boolean exists = userRepository.existsByUUID(UUID);
		if (!exists) {
			// throw exception
			return null;
		}
		// User user = userHelper.updateLastSeen(UUID);
		User user = userHelper.getUserByUUID(UUID);
		return user;
	}
}
