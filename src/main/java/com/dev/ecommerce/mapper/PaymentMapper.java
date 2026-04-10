package com.dev.ecommerce.mapper;

import com.dev.ecommerce.entity.Payment;
import com.dev.ecommerce.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

	public PaymentDTO toDTO(Payment payment) {
		return new PaymentDTO(
			payment.getId(),
			payment.getTimestamp());
	}

	public Payment toEntity(PaymentDTO paymentDTO) {
		return Payment.builder()
			.id(paymentDTO.id())
			.timestamp(paymentDTO.timestamp())
			.build();
	}
}
