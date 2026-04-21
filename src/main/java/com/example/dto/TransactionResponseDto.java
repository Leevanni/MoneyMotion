package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionResponseDto {

	private Long id;
	private LocalDate date;
	private Long userId;
	private BigDecimal amount;
	private String description;
	private String category;
	private LocalDateTime updatedAt;

}
