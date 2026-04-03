package com.br.marcus.saborfy.domain.payment.repository;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
