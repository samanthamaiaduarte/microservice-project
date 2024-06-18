package com.smd.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private LocalDateTime timestamp;
	private String status;
	private Integer statusCode;
	private String error;
	
	public ErrorMessage(HttpStatus status, String error) {
		this.timestamp = LocalDateTime.now();
		this.status = status.name();
		this.statusCode = status.value();
		this.error = error;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatusCode (Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}
