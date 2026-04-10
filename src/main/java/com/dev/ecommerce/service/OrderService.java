package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Order;
import com.dev.ecommerce.repository.OrderRepository;
import com.dev.ecommerce.mapper.OrderMapper;
import com.dev.ecommerce.dto.request.OrderRequestDTO;
import com.dev.ecommerce.dto.response.OrderResponseDTO;
import com.dev.ecommerce.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final UserRepository userRepository;

	public OrderService(OrderRepository orderRepository,
		OrderMapper orderMapper, UserRepository userRepository) {

		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.userRepository = userRepository;
	}

	public List<OrderResponseDTO> listAllOrders() {
		return orderRepository.findAll()
			.stream()
			.map(order -> orderMapper.toDTO(order))
			.collect(Collectors.toList());
	}

	public OrderResponseDTO createOrder(Long userId) {
		Order order = new Order();
		order.setUser(userRepository.getReferenceById(userId));
		orderRepository.save(order);
		return orderMapper.toDTO(order);
	}

	public OrderResponseDTO updateOrder(Long orderId,
		OrderRequestDTO orderRequestDTO) {
		Order order = orderRepository.getReferenceById(orderId);
		order.setStatus(orderRequestDTO.status());
		orderRepository.save(order);

		return orderMapper.toDTO(order);
	}

	public boolean deleteOrder(Long orderId) {
		if (orderRepository.existsById(orderId)) {
			orderRepository.deleteById(orderId);
			return true;
		}
		return false;
	}
}
