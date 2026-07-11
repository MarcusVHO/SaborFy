package com.br.marcus.saborfy.domain.customer.query;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerFinder {
    private final CustomerRepository repository;

    public CustomerFinder(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer byId(Long id) {
        return repository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
