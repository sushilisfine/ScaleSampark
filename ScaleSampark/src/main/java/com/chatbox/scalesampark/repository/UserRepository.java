package com.chatbox.scalesampark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatbox.scalesampark.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}