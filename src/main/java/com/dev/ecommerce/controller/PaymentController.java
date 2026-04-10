package com.dev.ecommerce.controller;

import com.dev.ecommerce.service.PaymentService;
import com.dev.ecommerce.dto.PaymentDTO;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@GetMapping
	public ResponseEntity<List<PaymentDTO>> listAllPayments() {
		return ResponseEntity.ok(paymentService.listAllPayments());
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<?> listPaymentByOrderId(@PathVariable Long orderId) {
		try {
			return ResponseEntity.ok(paymentService.listPaymentByOrderId(orderId));
		} catch (EntityNotFoundException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/{orderId}")
	public ResponseEntity<PaymentDTO> createPayment(@PathVariable Long orderId) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(paymentService.createPayment(orderId));
		} catch (DataIntegrityViolationException exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
		if (paymentService.deletePayment(paymentId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.internalServerError().build();
	}
}
