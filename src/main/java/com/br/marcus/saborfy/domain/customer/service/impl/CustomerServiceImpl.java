package com.br.marcus.saborfy.domain.customer.service.impl;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.factory.CustomerFactory;
import com.br.marcus.saborfy.domain.customer.mapper.CustomerMapper;
import com.br.marcus.saborfy.domain.customer.query.CustomerFinder;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.domain.customer.service.CustomerService;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerFactory customerFactory;
    private final CustomerMapper mapper;
    private final CustomerFinder customerFinder;

    public CustomerServiceImpl(CustomerRepository repository, CustomerFactory customerFactory, CustomerMapper mapper, CustomerFinder customerFinder) {
        this.repository = repository;
        this.customerFactory = customerFactory;
        this.mapper = mapper;
        this.customerFinder = customerFinder;
    }

    @Transactional
    public CustomerResponseDTO create (CreateCustomerRequest request, AuthenticatedUser user) {
        Customer newCustomer = repository.save(customerFactory.create(request,  user));
        return mapper.toResponse(newCustomer);
    }

    @Transactional
    public void delete(Long customerId) {
        Customer customer = customerFinder.byId(customerId);
        repository.delete(customer);
    }

    public List<CustomerResponseDTO> findCustomers(String name) {
        if (name != null && !name.isBlank()) {
            return mapper.toResponseList(repository.findCustomerByNameLike("%" + name + "%"));
        }
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional
    public CustomerResponseDTO update(AuthenticatedUser user, Long customerId, UpdateCustomerRequest request) {
        Customer customer = customerFinder.byId(customerId);
        customer.changeLatestUpdatedBy(user.id());
        customer.changeName(request.name());
        return mapper.toResponse(customer);
    }


}
