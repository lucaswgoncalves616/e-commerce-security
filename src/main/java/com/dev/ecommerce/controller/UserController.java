package com.dev.ecommerce.controller;

import com.dev.ecommerce.service.PhotoService;
import com.dev.ecommerce.service.UserService;
import com.dev.ecommerce.dto.request.UserRequestDTO;
import com.dev.ecommerce.dto.response.UserResponseDTO;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	private final PhotoService photoService;

	public UserController(UserService userService, PhotoService photoService) {
		this.userService = userService;
        this.photoService = photoService;
    }

	@GetMapping("/showAll")
	public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
		return ResponseEntity.ok(userService.listAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> listUserById(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(userService.listUserById(userId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("createUser")
	public ResponseEntity<UserResponseDTO> createUser(
		@RequestBody @Valid UserRequestDTO userRequestDTO,
		@RequestParam MultipartFile file) throws IOException {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(userService.createUser(userRequestDTO, photoService.uploadPhoto(file)));
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
		@RequestBody @Valid UserRequestDTO userRequestDTO) {

		try {
			return ResponseEntity.ok()
				.body(userService.updateUser(userId, userRequestDTO));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (userService.deleteUser(userId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}
}
