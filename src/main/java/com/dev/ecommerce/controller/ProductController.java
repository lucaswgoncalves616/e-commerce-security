package com.dev.ecommerce.controller;

import com.dev.ecommerce.service.ProductService;
import com.dev.ecommerce.dto.request.ProductRequestDTO;
import com.dev.ecommerce.dto.response.ProductResponseDTO;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<Set<ProductResponseDTO>> listAllProducts() {
		return ResponseEntity.ok(productService.listAllProducts());
	}

	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(
		@RequestBody @Valid ProductRequestDTO productRequestDTO) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(productService.createProduct(productRequestDTO));
	}
}
