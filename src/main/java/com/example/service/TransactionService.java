package com.example.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.dto.TransactionRequestDto;
import com.example.dto.TransactionResponseDto;
import com.example.entity.TransactionEntity;
import com.example.repository.TransactionRespository;

@Service
public class TransactionService {
	
	
	private TransactionRespository respository;
	
	public TransactionService(TransactionRespository respository) {
		this.respository = respository;
	}
	
	public TransactionResponseDto createTransaction(TransactionRequestDto transaction) {
		
		if (transaction.getAmount() == null || transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount must be greater than 0");
		}
		
		TransactionEntity entity = new TransactionEntity();
		
		entity.setDate(transaction.getDate());
		entity.setUserId(transaction.getUserId());
		entity.setAmount(transaction.getAmount());
		entity.setDescription(transaction.getDescription());
		entity.setCategory(transaction.getCategory());
		
		TransactionEntity saved = respository.save(entity);
		
		TransactionResponseDto response = new TransactionResponseDto();
		
		response.setId(saved.getId());
		response.setDate(saved.getDate());
		response.setUserId(saved.getUserId());
		response.setAmount(saved.getAmount());
		response.setDescription(saved.getDescription());
		response.setCategory(saved.getCategory());
		
		return response;
	}
}
