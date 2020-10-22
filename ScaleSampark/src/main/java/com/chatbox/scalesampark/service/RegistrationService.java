package com.chatbox.scalesampark.service;

import com.chatbox.scalesampark.dto.request.RegistrationRequestDTO;
import com.chatbox.scalesampark.dto.response.ParticipantsResponseDTO;
import com.chatbox.scalesampark.entity.user.User;

public interface RegistrationService {

	public User register(RegistrationRequestDTO registerationDTO);

	public ParticipantsResponseDTO getAllParticipants();

	public boolean removeParticipant(Integer uuid);

}
