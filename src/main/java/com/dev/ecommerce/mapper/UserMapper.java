package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.User;
import com.dev.ecommerce.dto.request.UserRequestDTO;
import com.dev.ecommerce.dto.response.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	private final PasswordEncoder passwordEncoder;

	public UserMapper(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(
			user.getId(),
			user.getName(),
			user.getEmail(),
			user.getPhoneNumber(),
			user.getRole(),
			user.getOrders());
	}

	public User toEntity(UserRequestDTO userRequestDTO) {
		return User.builder()
			.name(userRequestDTO.name())
			.email(userRequestDTO.email())
			.phoneNumber(userRequestDTO.phoneNumber())
			.password(passwordEncoder.encode(userRequestDTO.password()))
			.role(userRequestDTO.role())
			.build();
	}

}
