package com.br.marcus.saborfy.domain.customer.repository;

import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
}
