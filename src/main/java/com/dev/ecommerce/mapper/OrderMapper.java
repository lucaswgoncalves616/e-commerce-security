package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.Order;
import com.dev.ecommerce.dto.request.OrderRequestDTO;
import com.dev.ecommerce.dto.response.OrderResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

	public OrderResponseDTO toDTO(Order order) {
		return new OrderResponseDTO(
			order.getId(),
			order.getTimestamp(),
			order.getStatus());
	}

	public Order toEntity(OrderRequestDTO orderRequestDTO) {
		return Order.builder()
			.id(orderRequestDTO.id())
			.status(orderRequestDTO.status())
			.build();
	}
}
