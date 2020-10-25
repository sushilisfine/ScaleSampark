package com.chatbox.scalesampark.entity.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Index;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.chatbox.scalesampark.entity.message.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "user", indexes = { @Index(name = "UUID_idx", columnList = "UUID", unique = true),
		@Index(name = "email_idx", columnList = "email", unique = true) })
public class User implements Serializable {

	private static final long serialVersionUID = 4193921919526248751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(value = "participant uuid")
	private Integer id;

	private String UUID;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String email;

	private String nickname;

	@JsonProperty(value = "last seen")
	private LocalDateTime lastSeen;

	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonManagedReference
	@OneToMany(mappedBy = "user", targetEntity = Message.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Message> messageList = new ArrayList<>();

}