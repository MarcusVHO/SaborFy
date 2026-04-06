package com.br.marcus.saborfy.domain.customer.repository;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByName(String name);

    List<Customer> findCustomerByNameLike(String name);


}
