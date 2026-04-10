package com.dev.ecommerce.entity;

import com.dev.ecommerce.enums.OrderStatus;
import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Instant timestamp = Instant.now();

	// 0 = WAITING_FOR_PAYMENT
	// 1 = PAID
	// 2 = SENT
	// 3 = DELIVERED
	// 4 = CANCELLED
	@Column(nullable = false)
	private OrderStatus status = OrderStatus.WAITING_FOR_PAYMENT;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private User user;

	@OneToOne(
	mappedBy = "order",
	cascade = CascadeType.ALL,
	orphanRemoval = true)
	private Payment payment;
}
