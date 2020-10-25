package com.chatbox.scalesampark.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MessageDTO implements Serializable {

	private static final long serialVersionUID = 4193921919526248751L;

	@JsonProperty(value = "message_uuid")
	private String UUID;

	private String message;

	@JsonProperty(value = "participant_uuid")
	private String participantUUID;

	@JsonProperty(value = "message_type")
	private String messageType;
}
