package com.dev.ecommerce.controller;

import com.dev.ecommerce.dto.CategoryDTO;
import com.dev.ecommerce.service.CategoryService;
import java.util.Set;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<Set<CategoryDTO>> listAllCategories() {
		return ResponseEntity.ok(categoryService.listAllCategories());
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(
		@RequestBody @Valid CategoryDTO categoryDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(categoryService.createCategory(categoryDTO));
	}

	@PostMapping("/{categoryId}/{productId}")
	public ResponseEntity<CategoryDTO> setProductCategory(
		@PathVariable Long categoryId, @PathVariable Long productId) {

		return ResponseEntity.ok(
			categoryService.setProductCategory(categoryId, productId));
	}
}
