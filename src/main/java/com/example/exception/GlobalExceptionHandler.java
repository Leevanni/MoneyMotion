package com.example.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.dto.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleResourceNotFound(
			ResourceNotFoundException ex,
			HttpServletRequest request
			) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;

		ApiErrorResponse errorResponse = new ApiErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI()
		);
		
		return ResponseEntity.status(status).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				ex.getMessage(),
				request.getRequestURI()
		);
		
		return ResponseEntity.status(status).body(errorResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGenericException(
			Exception ex,
			HttpServletRequest request ) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ApiErrorResponse errorResponse = new ApiErrorResponse(
				LocalDateTime.now(),
				status.value(),
				status.getReasonPhrase(),
				"An unexpected error occurred",
				request.getRequestURI()
		);
		
		return ResponseEntity.status(status).body(errorResponse);
	}
}
