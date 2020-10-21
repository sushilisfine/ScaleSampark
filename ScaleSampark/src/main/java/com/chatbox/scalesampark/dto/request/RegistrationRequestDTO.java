package com.chatbox.scalesampark.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequestDTO implements Serializable {

	private static final long serialVersionUID = 4193921919526267351L;

	@NotNull(message = "Please provide email id")
	@NotBlank(message = "Please provide email id")
	private String email;

	@NotNull(message = "Please provide nickname")
	@NotBlank(message = "Please provide nickname")
	private String nickname;

}
