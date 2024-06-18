package com.smd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {
	
    private ErrorMessage errorMessage(HttpStatus statusHttp, String message){
    	ErrorMessage responseError = new ErrorMessage(statusHttp, message);
        return responseError;
    }
    
    @ExceptionHandler({RecordNotFoundException.class})
    private ResponseEntity<ErrorMessage> handleRecordNotFoundException(RecordNotFoundException e, WebRequest request) {
    	ErrorMessage error = errorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }  
    
    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ErrorMessage> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest request) {
    	ErrorMessage error = errorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorMessage> handleUnexpected(Throwable exception) {
		ErrorMessage error = errorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected server error");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
