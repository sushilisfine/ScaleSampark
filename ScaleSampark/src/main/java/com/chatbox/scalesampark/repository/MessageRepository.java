package com.chatbox.scalesampark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatbox.scalesampark.entity.message.Message;
import com.chatbox.scalesampark.entity.user.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findAllByPending(boolean b);

	void deleteAllByUser(User user);

}