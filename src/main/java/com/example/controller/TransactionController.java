package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TransactionRequestDto;
import com.example.dto.TransactionResponseDto;
import com.example.entity.TransactionEntity;
import com.example.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private TransactionService service;
	
	public TransactionController(TransactionService service) {
		this.service = service;
	}
	
	@PostMapping
	public TransactionResponseDto create(@RequestBody @Valid TransactionRequestDto request) {
		return service.createTransaction(request);
	}
}
