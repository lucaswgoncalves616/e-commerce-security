package com.dev.ecommerce.dto.request;

import com.dev.ecommerce.enums.OrderStatus;

public record OrderRequestDTO(
	OrderStatus status) {
}
