package com.chatbox.scalesampark.dto.response;

import java.io.Serializable;
import java.util.List;

import com.chatbox.scalesampark.entity.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantsResponseDTO implements Serializable {

	private static final long serialVersionUID = 4193567919526267351L;

	private List<User> participants;

}
