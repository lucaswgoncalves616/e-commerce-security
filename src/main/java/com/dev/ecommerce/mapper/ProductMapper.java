package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.dto.request.ProductRequestDTO;
import com.dev.ecommerce.dto.response.ProductResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public ProductResponseDTO toDTO(Product product) {
		return new ProductResponseDTO(
			product.getId(),
			product.getName(),
			product.getDescription(),
			product.getPrice(),
			product.getImgUrl(),
			product.getCategories());
	}

	public Product toEntity(ProductRequestDTO productRequestDTO) {
		return Product.builder()
			.name(productRequestDTO.name())
			.description(productRequestDTO.description())
			.price(productRequestDTO.price())
			.imgUrl(productRequestDTO.imgUrl())
			.build();
	}
}
