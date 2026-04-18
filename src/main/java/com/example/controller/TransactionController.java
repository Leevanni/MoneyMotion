package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CreateTransactionRequestDto;
import com.example.dto.TransactionResponseDto;
import com.example.dto.UpdateTransactionRequestDto;
import com.example.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;
	
	public TransactionController(TransactionService service) {
		this.transactionService = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TransactionResponseDto> createTransaction(
			@RequestBody CreateTransactionRequestDto request) {
		
		TransactionResponseDto reponse = transactionService.createTransaction(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
	}
	
	@GetMapping
	public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
		List<TransactionResponseDto> transactionList = transactionService.getAllTransactions();
		
		return ResponseEntity.ok(transactionList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponseDto> getTransaction(@PathVariable Long id) {
		TransactionResponseDto response = transactionService.getTransactionById(id);
	
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<TransactionResponseDto> updateTransaction(
			@PathVariable Long id, 
			@RequestBody UpdateTransactionRequestDto request) {
		
		TransactionResponseDto response = transactionService.updateTransaction(id, request);
		
		
		return ResponseEntity.ok(response);
	}
}
