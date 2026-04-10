package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.User;
import com.dev.ecommerce.repository.UserRepository;
import com.dev.ecommerce.mapper.UserMapper;
import com.dev.ecommerce.dto.request.UserRequestDTO;
import com.dev.ecommerce.dto.response.UserResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<UserResponseDTO> listAllUsers() {
		return userRepository.findAll()
			.stream()
			.map(user -> userMapper.toDTO(user))
			.collect(Collectors.toList());
	}

	public UserResponseDTO listUserById(Long userId) {
		return userMapper.toDTO(userRepository.getReferenceById(userId));
	}

	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		return userMapper.toDTO(
			userRepository.save(userMapper.toEntity(userRequestDTO)));
	}

	public UserResponseDTO updateUser(Long userId,
		UserRequestDTO userRequestDTO) {

		User user = userRepository.getReferenceById(userId);
			user.setName(userRequestDTO.name());
			user.setEmail(userRequestDTO.email());
			user.setPhoneNumber(userRequestDTO.phoneNumber());
			user.setPassword(userRequestDTO.password());
			user.setRole(userRequestDTO.role());

		userRepository.save(user);
		return userMapper.toDTO(user);
	}

	public boolean deleteUser(Long userId) {
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

}
