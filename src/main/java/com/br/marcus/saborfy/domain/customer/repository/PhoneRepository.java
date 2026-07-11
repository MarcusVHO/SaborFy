package com.br.marcus.saborfy.domain.customer.repository;

import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<CustomerPhone, Long> {
    Optional<CustomerPhone> findByIdAndCustomerId(Long id, Long customerId);
}
