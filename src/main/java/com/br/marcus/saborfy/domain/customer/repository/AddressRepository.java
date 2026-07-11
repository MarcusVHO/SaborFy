package com.br.marcus.saborfy.domain.customer.repository;

import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<CustomerAddress, Long> {
    Optional<CustomerAddress> findByIdAndCustomerId(Long id, Long customerId);
}
