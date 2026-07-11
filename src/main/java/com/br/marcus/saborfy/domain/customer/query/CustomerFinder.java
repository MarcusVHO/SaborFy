package com.br.marcus.saborfy.domain.customer.query;

import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.mapper.CustomerMapper;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerFinder {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerFinder(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CustomerResponseDTO byId(Long id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(CustomerNotFoundException::new));
    }
    public Customer findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
