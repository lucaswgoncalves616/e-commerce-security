package com.dev.ecommerce.dto;

import java.time.Instant;

public record PaymentDTO(
	Long id,
	Instant timestamp) {
}
