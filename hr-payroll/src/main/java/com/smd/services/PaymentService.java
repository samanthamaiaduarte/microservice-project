package com.smd.services;

import org.springframework.stereotype.Service;

import com.smd.entities.Payment;

@Service
public class PaymentService {

	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Mocked Worker", 200.0, days);
	}
}
