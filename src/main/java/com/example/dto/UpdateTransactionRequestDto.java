package com.example.dto;

import lombok.Data;

@Data
public class UpdateTransactionRequestDto {
	private String description;
	private String category;
}
