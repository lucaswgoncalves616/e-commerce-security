package com.dev.ecommerce.repository;

import com.dev.ecommerce.entity.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
