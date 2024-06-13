package com.smd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.entities.Worker;
import com.smd.exceptions.RecordNotFoundException;
import com.smd.repositories.WorkerRepository;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;
	
	public List<Worker> findAll() {
		return repository.findAll();
	}
	
	public Worker findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Worker not found"));
	}
}
