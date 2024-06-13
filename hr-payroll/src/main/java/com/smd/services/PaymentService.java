package com.smd.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.smd.entities.Payment;
import com.smd.entities.Worker;
import com.smd.exceptions.RecordNotFoundException;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, String> params = new HashMap<>();
		params.put("id", workerId.toString());
		
		try {
			Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, params);
			return new Payment(worker.getName(), worker.getDailyIncome(), days);
		}
		catch(RestClientException e) {
			throw new RecordNotFoundException("Worker not found.");
		}
	}
}
