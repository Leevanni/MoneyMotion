package com.example.service;

import java.math.BigDecimal;
import java.util.Optional;

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
	
	public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto transaction) {
		
		Optional<TransactionEntity> entity = respository.findById(id);
		
		if (hasEditableFields(transaction)) {
			
		} else {
			// No changes method
		}
		
		return null;
		
	}
	
	public boolean hasEditableFields(TransactionRequestDto transaction) {
		return transaction.getAmount() != null
		        || transaction.getCategory() != null
		        || transaction.getDescription() != null;
	}
	
	public String normalizeTextField(String value) throws IllegalArgumentException {
	    if (value == null) {
	        return null;
	    }
	    
	    String trimmedValue = value.trim();

	    if (trimmedValue.isBlank()) {
	        throw new IllegalArgumentException("Value cannot be blank");
	    }

	    return trimmedValue.toLowerCase();
	}
	
	public boolean hasChanges(TransactionRequestDto request, TransactionEntity entity) throws IllegalArgumentException {
		
		if (request.getAmount() != null) {
			if (request.getAmount().compareTo(entity.getAmount()) != 0) {
				return true;
			}
		}
		
		if (request.getDescription() != null) {
			String normalizedRequestDescription = normalizeTextField(request.getDescription());
			String normalizedEntityDescription = normalizeTextField(entity.getDescription());
			
			if (!normalizedRequestDescription.equals(normalizedEntityDescription)) {
				return true;
			}
		}
		
		if (request.getCategory() != null) {
			String normalizedRequestCategory = normalizeTextField(request.getCategory());
			String normalizedEntityCategory = normalizeTextField(entity.getCategory());
			
			if (!normalizedRequestCategory.equals(normalizedEntityCategory)) {
				return true;
			}
		}
		
		
		return false;
		
	}
	
	public boolean textFieldChanged(String textOne, String textTwo) {
		return textOne.equals(textTwo);
	}
	
	
}
