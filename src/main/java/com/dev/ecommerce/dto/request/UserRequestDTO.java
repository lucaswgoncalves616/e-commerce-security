package com.dev.ecommerce.dto.request;

import com.dev.ecommerce.enums.Role;

public record UserRequestDTO(
	String name,
	String email,
	String phoneNumber,
	String password,
	Role role) {
}
