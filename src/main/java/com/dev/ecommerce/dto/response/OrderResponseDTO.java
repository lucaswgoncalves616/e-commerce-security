package com.dev.ecommerce.dto.response;

import com.dev.ecommerce.enums.OrderStatus;
import java.time.Instant;

public record OrderResponseDTO(
	Long id,
	Instant timestamp,
	OrderStatus status) {
}
