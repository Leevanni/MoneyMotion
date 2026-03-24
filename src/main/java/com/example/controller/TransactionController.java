package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OnCreate;
import com.example.dto.OnUpdate;
import com.example.dto.TransactionRequestDto;
import com.example.dto.TransactionResponseDto;
import com.example.entity.TransactionEntity;
import com.example.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService service;
	
	public TransactionController(TransactionService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<TransactionResponseDto> createTransaction(
			@RequestBody @Validated(OnCreate.class) TransactionRequestDto request) {
		
		TransactionResponseDto reponse = service.createTransaction(request);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("transactions/{id}")
	public ResponseEntity<TransactionResponseDto> updateTransaction(
			@PathVariable Long id, 
			@RequestBody @Validated(OnUpdate.class) TransactionRequestDto request) {
		
		
		
		return ResponseEntity.ok().build();
	}
}
