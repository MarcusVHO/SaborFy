package com.br.marcus.saborfy.customer;

import com.br.marcus.saborfy.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByNome(String nome);
}
