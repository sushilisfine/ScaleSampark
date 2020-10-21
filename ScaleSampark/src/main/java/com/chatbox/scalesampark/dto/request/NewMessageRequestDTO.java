package com.chatbox.scalesampark.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewMessageRequestDTO implements Serializable {

	private static final long serialVersionUID = 4193921919525635351L;

	@NotNull(message = "Please provide participants UUID")
	@JsonProperty(value = "participant uuid")
	private Integer participantUUID;

	@NotNull(message = "Please provide message type")
	@JsonProperty(value = "message type")
	private Integer messageType;

	@NotNull(message = "Please provide message")
	@NotBlank(message = "Please provide message")
	private String message;

}
