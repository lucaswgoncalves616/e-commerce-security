package com.dev.ecommerce.entity;

import com.dev.ecommerce.enums.Role;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "phone_number", length = 11, nullable = false)
	private String phoneNumber;

	@Column(length = 128, nullable = false)
	private String password;

	// 0 = Admin
	// 1 = User
	@Column(nullable = false)
	private Role role;

	@OneToMany(
	mappedBy = "user",
	cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
}
