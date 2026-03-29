package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import com.br.marcus.saborfy.domain.user.entity.User;
import org.springframework.stereotype.Service;



@Service
public class UpdateCustomerService {
    private final CustomerRepository customerRepository;

    public UpdateCustomerService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer update(AuthenticatedUser user, Long customerId, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);

        User newUser = new User();
        newUser.setId(user.id());

        customer.setLatestUpdateBy(newUser);
        customer.setName(request.name());

        customerRepository.save(customer);
        return customer;
    }
}
