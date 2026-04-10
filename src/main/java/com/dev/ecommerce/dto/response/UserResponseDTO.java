package com.dev.ecommerce.dto.response;

import java.util.List;
import com.dev.ecommerce.entity.Order;
import com.dev.ecommerce.enums.Role;

public record UserResponseDTO(
	Long id,
	String name,
	String email,
	String phoneNumber,
	Role role,
	List<Order> orders) {
}
