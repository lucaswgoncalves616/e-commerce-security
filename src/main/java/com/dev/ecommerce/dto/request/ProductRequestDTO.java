package com.dev.ecommerce.dto.request;

public record ProductRequestDTO(
	String name,
	String description,
	double price,
	String imgUrl) {
}
