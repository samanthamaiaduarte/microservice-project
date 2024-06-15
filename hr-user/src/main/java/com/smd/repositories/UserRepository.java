package com.smd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smd.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
