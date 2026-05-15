package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.CreateTransactionRequestDto;
import com.example.dto.TransactionResponseDto;
import com.example.dto.UpdateTransactionRequestDto;
import com.example.entity.TransactionEntity;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.TransactionRespository;

@Service
public class TransactionService {


	private final TransactionRespository respository;

	public TransactionService(TransactionRespository respository) {
		this.respository = respository;
	}

	public List<TransactionResponseDto> getAllTransactions() {
		return respository.findAll()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	public TransactionResponseDto getTransactionById(Long id) {
		TransactionEntity entity = respository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

		return mapToResponse(entity);
	}

	public TransactionResponseDto createTransaction(CreateTransactionRequestDto transaction) {

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

		return mapToResponse(saved);
	}

	public TransactionResponseDto updateTransaction(Long id, UpdateTransactionRequestDto transaction) {

		if (!hasEditableFields(transaction)) {
			throw new IllegalArgumentException("At least one editable field must be provided");
		}

		TransactionEntity entity = respository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

		if (!hasChanges(transaction, entity)) {
			throw new IllegalArgumentException("No changes detected");
		}

		applyUpdates(transaction, entity);

		entity.setUpdatedAt(LocalDateTime.now());
		TransactionEntity saved = respository.save(entity);

		return mapToResponse(saved);
	}

	private boolean hasEditableFields(UpdateTransactionRequestDto transaction) {
		        return transaction.getCategory() != null
		        || transaction.getDescription() != null;
	}

	private String normalizeTextField(String value) throws IllegalArgumentException { // This methods determins if editable fields are valid
	    if (value == null) {
	        return null;
	    }

	    String trimmedValue = value.trim();

	    if (trimmedValue.isBlank()) {
	        throw new IllegalArgumentException("Value cannot be blank");
	    }

	    return trimmedValue.toLowerCase();
	}

	private boolean hasChanges(UpdateTransactionRequestDto request, TransactionEntity entity) throws IllegalArgumentException {

		if (request.getDescription() != null) {
			String normalizedRequestDescription = normalizeTextField(request.getDescription());
			String normalizedEntityDescription = normalizeTextField(entity.getDescription());

			if (textFieldChanged(normalizedRequestDescription, normalizedEntityDescription)) {
				return true;
			}
		}

		if (request.getCategory() != null) {
			String normalizedRequestCategory = normalizeTextField(request.getCategory());
			String normalizedEntityCategory = normalizeTextField(entity.getCategory());

			if (textFieldChanged(normalizedRequestCategory, normalizedEntityCategory)) {
				return true;
			}
		}

		return false;
	}

	private boolean textFieldChanged(String textOne, String textTwo) {
		return !textOne.equals(textTwo);
	}

	private void applyUpdates(UpdateTransactionRequestDto request, TransactionEntity entity) {

		if (request.getDescription() != null) {
			String requestDescription = request.getDescription();
			String entityDescription = entity.getDescription();

			String normalizedRequestDescription = normalizeTextField(requestDescription);
			String normalizedEntityDescription = normalizeTextField(entityDescription);

			if (textFieldChanged(normalizedRequestDescription, normalizedEntityDescription)) {
				entity.setDescription(requestDescription.trim());
			}
		}

		if (request.getCategory() != null) {
			String requestCategory = request.getCategory();
			String entityCategory = entity.getCategory();


			String normalizedRequestCategory = normalizeTextField(requestCategory);
			String normalizedEntityCategory = normalizeTextField(entityCategory);

			if (textFieldChanged(normalizedRequestCategory, normalizedEntityCategory)) {
				entity.setCategory(normalizedRequestCategory);
			}
		}
	}

	private TransactionResponseDto mapToResponse(TransactionEntity entity) {
		TransactionResponseDto response = new TransactionResponseDto();

		response.setId(entity.getId());
		response.setDate(entity.getDate());
		response.setUserId(entity.getUserId());
		response.setAmount(entity.getAmount());
		response.setDescription(entity.getDescription());
		response.setCategory(entity.getCategory());
		response.setUpdatedAt(entity.getUpdatedAt());
		return response;
	}
	
	public void deleteTransaction(long id) {

	}
}
