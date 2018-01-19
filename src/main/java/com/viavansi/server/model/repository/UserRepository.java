package com.viavansi.server.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viavansi.server.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findOneByUsername(String username);
}

