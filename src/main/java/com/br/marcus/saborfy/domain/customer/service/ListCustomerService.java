package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCustomerService {
    public final CustomerRepository customerRepository;

    public ListCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }
}
