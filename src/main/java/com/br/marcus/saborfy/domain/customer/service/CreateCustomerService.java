package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.domain.customer.utils.CustomerUtils;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import com.br.marcus.saborfy.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {
    private final CustomerRepository customerRepository;

    public CreateCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create (CreateCustomerRequest request, AuthenticatedUser user) {
        Customer newCustomer = new Customer();
        newCustomer.setName(request.name());
        User newUser = new User();
        newUser.setId(user.id());
        newCustomer.setUser(newUser);
        newCustomer.setLatestUpdateBy(newUser);

        CustomerUtils.addPhonesInCustomer(request.phones(), newCustomer, newUser);
        CustomerUtils.addAddressesInCustomer(request.addresses(), newCustomer, newUser);

        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
