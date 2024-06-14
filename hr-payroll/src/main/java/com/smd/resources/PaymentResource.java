package com.smd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.smd.entities.Payment;
import com.smd.exceptions.RecordNotFoundException;
import com.smd.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;
	
	@HystrixCommand(ignoreExceptions = { RecordNotFoundException.class }, fallbackMethod = "getPaymentAlt")
	@GetMapping("/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
		Payment payment = service.getPayment(workerId, days);
		return ResponseEntity.ok(payment);
	}
	
	public ResponseEntity<Payment> getPaymentAlt(Long workerId, Integer days) {
		Payment payment = new Payment("Worker is out", 0.0, days);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(payment);
	}
}
