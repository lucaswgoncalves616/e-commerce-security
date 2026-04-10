package com.dev.ecommerce.controller;

import com.dev.ecommerce.service.OrderService;
import com.dev.ecommerce.dto.request.OrderRequestDTO;
import com.dev.ecommerce.dto.response.OrderResponseDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<OrderResponseDTO>> listAllOrders() {
		return ResponseEntity.ok(orderService.listAllOrders());
	}

	@PostMapping("/{userId}")
	public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Long userId) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.createOrder(userId));
		} catch (DataIntegrityViolationException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<OrderResponseDTO> updateOrder(
		@PathVariable Long orderId, @RequestBody @Valid OrderRequestDTO orderRequestDTO) {
		try {
			return ResponseEntity.ok()
				.body(orderService.updateOrder(orderId, orderRequestDTO));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
		if (orderService.deleteOrder(orderId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}
}
