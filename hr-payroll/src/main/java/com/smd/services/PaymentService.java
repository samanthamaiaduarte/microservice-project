package com.smd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.entities.Payment;
import com.smd.entities.Worker;
import com.smd.exceptions.RecordNotFoundException;
import com.smd.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
	@Autowired
	private WorkerFeignClient feignClient;

	public Payment getPayment(Long workerId, Integer days) {
		try {
			Worker worker = feignClient.findById(workerId).getBody();
			return new Payment(worker.getName(), worker.getDailyIncome(), days);
		}
		catch(RuntimeException e) {
			throw new RecordNotFoundException("Worker not found.");
		}
	}
}
