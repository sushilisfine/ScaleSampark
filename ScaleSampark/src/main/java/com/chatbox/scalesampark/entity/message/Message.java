package com.chatbox.scalesampark.entity.message;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chatbox.scalesampark.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 4193921919526248751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonProperty(value = "message_uuid")
	private String UUID;

	private LocalDateTime createdAt;

	private String message;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_uuid")
	@JsonProperty(value = "participant_uuid")
	private User user;

	@JsonProperty(value = "message_type")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "message_type_id")
	private MessageType messageType;

}
