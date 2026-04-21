package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateTransactionRequestDto {

	@NotNull(message = "Transaction date is required")
	private LocalDate date;

	@NotNull(message = "User ID is required")
	private Long userId;

	@NotNull(message = "User ID is required")

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must greater than zero")
	@Digits(integer = 10, fraction = 2, message = "Amount must be valid monetary value")
	private BigDecimal amount;

	private String description;
	private String category;
}
