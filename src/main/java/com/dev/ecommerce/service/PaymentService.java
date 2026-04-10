package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Payment;
import com.dev.ecommerce.repository.PaymentRepository;
import com.dev.ecommerce.mapper.PaymentMapper;
import com.dev.ecommerce.dto.PaymentDTO;
import com.dev.ecommerce.repository.OrderRepository;
import com.dev.ecommerce.entity.Order;
import com.dev.ecommerce.enums.OrderStatus;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	private final OrderRepository orderRepository;

	public PaymentService(PaymentRepository paymentRepository,
		PaymentMapper paymentMapper, OrderRepository orderRepository) {

		this.paymentRepository = paymentRepository;
		this.paymentMapper = paymentMapper;
		this.orderRepository = orderRepository;
	}

	public List<PaymentDTO> listAllPayments() {
		return paymentRepository.findAll()
			.stream()
			.map(payment -> paymentMapper.toDTO(payment))
			.collect(Collectors.toList());
	}

	public PaymentDTO listPaymentByOrderId(Long orderId) {
		return paymentMapper.toDTO(
			orderRepository.getReferenceById(orderId).getPayment());
	}

	public PaymentDTO createPayment(Long orderId) {
		Payment payment = new Payment();
		Order order = orderRepository.getReferenceById(orderId);

		payment.setOrder(order);
		order.setStatus(OrderStatus.PAID);

		paymentRepository.save(payment);
		orderRepository.save(order);
		return paymentMapper.toDTO(payment);
	}

	public boolean deletePayment(Long paymentId) {
		if (paymentRepository.existsById(paymentId)) {
			Payment payment = paymentRepository.getReferenceById(paymentId);
			Order order = payment.getOrder();

			order.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
			order.setPayment(null);

			paymentRepository.deleteById(paymentId);
			orderRepository.save(order);
			return true;
		}
		return false;
	}

}
