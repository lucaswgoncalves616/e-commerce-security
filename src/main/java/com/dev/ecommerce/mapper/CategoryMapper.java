package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.Category;
import com.dev.ecommerce.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

	public CategoryDTO toDTO(Category category) {
		return new CategoryDTO(
			category.getId(),
			category.getName());
	}

	public Category toEntity(CategoryDTO categoryDTO) {
		return Category.builder()
			.id(categoryDTO.id())
			.name(categoryDTO.name()).build();
	}

}
