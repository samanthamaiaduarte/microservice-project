package com.smd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.dto.UserDTO;
import com.smd.entities.User;
import com.smd.exceptions.RecordNotFoundException;
import com.smd.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public UserDTO findById(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found")); 
		return new UserDTO(user);
	}
	
	public UserDTO findByEmail(String email) {
		User user = repository.findByEmail(email.toLowerCase());
		
		if(user == null)
			throw new RecordNotFoundException("User not found");
		
		return new UserDTO(user); 
	}
}
