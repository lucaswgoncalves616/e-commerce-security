package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Category;
import com.dev.ecommerce.repository.CategoryRepository;
import com.dev.ecommerce.mapper.CategoryMapper;
import com.dev.ecommerce.dto.CategoryDTO;
import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.repository.ProductRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	private final ProductRepository productRepository;

	public CategoryService(CategoryRepository categoryRepository,
		CategoryMapper categoryMapper, ProductRepository productRepository) {

		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
		this.productRepository = productRepository;
	}

	public Set<CategoryDTO> listAllCategories() {
		return categoryRepository.findAll()
			.stream()
			.map(category -> categoryMapper.toDTO(category))
			.collect(Collectors.toSet());
	}

	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		return categoryMapper.toDTO(
			categoryRepository.save(categoryMapper.toEntity(categoryDTO)));
	}

	public CategoryDTO setProductCategory(Long categoryId, Long productId) {
		Category category = categoryRepository.getReferenceById(categoryId);
		Product product = productRepository.getReferenceById(productId);

		category.getProducts().add(product);
		product.getCategories().add(category);

		categoryRepository.save(category);
		productRepository.save(product);

		return categoryMapper.toDTO(category);
	}
}
