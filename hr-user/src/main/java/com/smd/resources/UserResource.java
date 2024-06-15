package com.smd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smd.dto.UserDTO;
import com.smd.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findAById(@PathVariable Long id) {
		UserDTO user = service.findById(id);
		return ResponseEntity.ok(user);	
	}
	
	@GetMapping("/search")
	public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
		UserDTO user = service.findByEmail(email);
		return ResponseEntity.ok(user);	
	}
}
