package com.chatbox.scalesampark.service.utility;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.chatbox.scalesampark.entity.user.User;
import com.chatbox.scalesampark.repository.UserRepository;

@Component
@Transactional
public class UserHelper {

	private UserRepository userRepository;

	private MessageHelper messageHelper;

	public UserHelper(UserRepository userRepository, MessageHelper messageHelper) {

		this.userRepository = userRepository;
		this.messageHelper = messageHelper;
	}

	public User updateLastSeen(String UUID) {
		User user = userRepository.findByUUID(UUID).orElse(null);
		if (user != null) {
			user.setLastSeen(LocalDateTime.now());
			user = userRepository.save(user);
		}
		return user;
	}

	public User getUserByUUID(String UUID) {
		User user = userRepository.findByUUID(UUID).orElse(null);
		return user;
	}

	public void removeUser(String UUID) {

		User user = userRepository.findByUUID(UUID).orElse(null);
		if (user != null) {
			messageHelper.removeMessagesForUser(user);
			userRepository.deleteById(user.getId());
		}
	}
}
