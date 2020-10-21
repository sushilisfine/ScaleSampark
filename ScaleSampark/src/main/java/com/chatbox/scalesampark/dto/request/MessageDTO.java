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

	@JsonProperty(value = "message uuid")
	private Integer uuid;

	private String message;

	@JsonProperty(value = "participant uuid")
	private Integer participantUUID;

	@JsonProperty(value = "message type")
	private Integer messageType;
}
