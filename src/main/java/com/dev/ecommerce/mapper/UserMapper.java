package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.User;
import com.dev.ecommerce.dto.request.UserRequestDTO;
import com.dev.ecommerce.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

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
			.id(userRequestDTO.id())
			.name(userRequestDTO.name())
			.email(userRequestDTO.email())
			.phoneNumber(userRequestDTO.phoneNumber())
			.password(userRequestDTO.password())
			.role(userRequestDTO.role())
			.build();
	}

}
