package com.dev.ecommerce.service;

import com.dev.ecommerce.repository.ProductRepository;
import com.dev.ecommerce.mapper.ProductMapper;
import com.dev.ecommerce.dto.request.ProductRequestDTO;
import com.dev.ecommerce.dto.response.ProductResponseDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public ProductService(ProductRepository productRepository,
		ProductMapper productMapper) {

		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	public Set<ProductResponseDTO> listAllProducts() {
		return productRepository.findAll()
			.stream()
			.map(product -> productMapper.toDTO(product))
			.collect(Collectors.toSet());
	}

	public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
		return productMapper.toDTO(
			productRepository.save(productMapper.toEntity(productRequestDTO)));
	}

}
