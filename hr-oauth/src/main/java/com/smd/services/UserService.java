package com.smd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.entities.User;
import com.smd.exceptions.RecordNotFoundException;
import com.smd.feignclients.UserFeignClient;

@Service
public class UserService {
	@Autowired
	private UserFeignClient feignClient;

	public User getUser(String email) {
		try {
			User user = feignClient.findByEmail(email).getBody();
			
			if(user == null)
				throw new RecordNotFoundException("Worker not found.");
			
			return user;
		}
		catch(RuntimeException e) {
			throw new RecordNotFoundException("Worker not found.");
		}
		
	}
}
