package com.br.marcus.saborfy.domain.customer.service.impl;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.domain.customer.service.CustomerService;
import com.br.marcus.saborfy.domain.customer.utils.CustomerUtils;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.repository = customerRepository;
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

        repository.save(newCustomer);
        return newCustomer;
    }

    public void delete(Long customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        repository.delete(customer);
    }

    public List<Customer> getCustomerList(String name) {
        if (name != null) {
            return repository.findCustomerByNameLike("%" + name + "%");
        }
        return repository.findAll();
    }

    public Customer update(AuthenticatedUser user, Long customerId, UpdateCustomerRequest request) {
        Customer customer = repository.findById(customerId).orElseThrow(CustomerNotFoundException::new);

        User newUser = new User();
        newUser.setId(user.id());

        customer.setLatestUpdateBy(newUser);
        customer.setName(request.name());

        repository.save(customer);
        return customer;
    }
}
