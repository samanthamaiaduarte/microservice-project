package com.smd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smd.entities.User;
import com.smd.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserFeignClient feignClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = feignClient.findByEmail(username).getBody();
		
		if(user == null)
			throw new UsernameNotFoundException("Worker not found.");
		
		return user;
	}
}
