package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

public class TransactionRequestDto {
	
	@Null(message = "Transaction ID must not be provided when creating a transaction", groups = OnCreate.class)
	@NotNull(message = "Transaction ID is required when updating a transaction", groups = OnUpdate.class)
	private Long id;
	
	@NotNull(message = "Transaction date is required", groups = { OnCreate.class, OnUpdate.class })
	private LocalDate date;
	
	@NotNull(message = "User ID is required", groups = { OnCreate.class, OnUpdate.class })
	private Long userId;
	
	@NotNull(message = "Amount is required", groups = { OnCreate.class, OnUpdate.class })
	@Positive(message = "Amount must be greater than zero", groups = { OnCreate.class, OnUpdate.class })
	private BigDecimal amount;
	
	private String description;
	
	private String category;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "TransactionRequestDto [id=" + id + ", date=" + date + ", userId=" + userId + ", amount=" + amount
				+ ", description=" + description + ", category=" + category + "]";
	}
}
