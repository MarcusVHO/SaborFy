package com.br.marcus.saborfy.domain.customer.repository;

import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPhoneRepository extends JpaRepository<CustomerPhone, Long> {
}
