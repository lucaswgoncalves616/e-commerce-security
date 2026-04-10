package com.dev.ecommerce.dto.response;

import java.util.Set;
import com.dev.ecommerce.entity.Category;

public record ProductResponseDTO(
	Long id,
	String name,
	String description,
	double price,
	String imgUrl,
	Set<Category> categories) {

}
