package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerService {
    private CustomerRepository customerRepository;

    public DeleteCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void delete(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }
}
