package com.example.dto;

public class UpdateTransactionRequestDto {

	private String description;
	private String category;
	
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
		return "UpdateTransactionRequestDto [description=" + description + ", category=" + category + "]";
	}
}
