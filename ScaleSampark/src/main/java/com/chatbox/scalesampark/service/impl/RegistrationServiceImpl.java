package com.chatbox.scalesampark.service.impl;

import java.time.LocalDateTime;
import java.util.List;

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

		User user = User.builder().email(registerationDTO.getEmail()).lastSeen(LocalDateTime.now())
				.nickname(registerationDTO.getNickname()).build();

		User registeredUser = userRepository.save(user);

		return registeredUser;
	}

	@Override
	public ParticipantsResponseDTO getAllParticipants(Integer uuid) {

		boolean exists = userRepository.existsById(uuid);
		if (!exists)
			return null;
		ParticipantsResponseDTO participants = new ParticipantsResponseDTO();
		List<User> users = userRepository.findAll();

		User requester = userHelper.updateLastSeen(uuid);

		if (requester != null)
			participants.setParticipants(users);

		return participants;
	}

	@Override
	public boolean removeParticipant(Integer uuid) {

		boolean exists = userRepository.existsById(uuid);
		if (exists) {
			userHelper.removeUser(uuid);
			return true;
		}
		return false;
	}
}
