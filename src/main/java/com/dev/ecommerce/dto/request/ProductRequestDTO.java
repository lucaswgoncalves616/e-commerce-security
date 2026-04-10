package com.dev.ecommerce.dto.request;

public record ProductRequestDTO(
	Long id,
	String name,
	String description,
	double price,
	String imgUrl) {

}
