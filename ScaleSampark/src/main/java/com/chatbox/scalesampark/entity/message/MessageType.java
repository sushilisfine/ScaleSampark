package com.chatbox.scalesampark.entity.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "message_type")
public class MessageType implements Serializable {

	private static final long serialVersionUID = 4193921919526248751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String messageType;

	@JsonManagedReference
	@OneToMany(mappedBy = "messageType", targetEntity = Message.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Message> messageList = new ArrayList<>();

}
