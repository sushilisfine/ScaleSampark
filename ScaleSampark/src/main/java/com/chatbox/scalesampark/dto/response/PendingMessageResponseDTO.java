package com.chatbox.scalesampark.dto.response;

import java.io.Serializable;
import java.util.List;

import com.chatbox.scalesampark.dto.request.MessageDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PendingMessageResponseDTO implements Serializable {

	private static final long serialVersionUID = 4193567919526267351L;

	private List<MessageDTO> pendingMessage;

}
