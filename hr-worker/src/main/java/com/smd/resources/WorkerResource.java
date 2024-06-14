package com.smd.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smd.entities.Worker;
import com.smd.services.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerService service;
	
	@GetMapping("/configs")
	public ResponseEntity<Void> getConfig() {
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();		
	}	
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> workers = service.findAll();
		return ResponseEntity.ok(workers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Worker> findAById(@PathVariable Long id) {
		logger.info("PORT = " + env.getProperty("local.server.port"));
		
		Worker worker = service.findById(id);
		return ResponseEntity.ok(worker);
	}	
}
