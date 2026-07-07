package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

import java.util.List;

public interface CustomerService {
    Customer create (CreateCustomerRequest request, AuthenticatedUser user);
    void delete(Long customerId);
    List<Customer> getCustomerList(String name);
    Customer update(AuthenticatedUser user, Long customerId, UpdateCustomerRequest request);
}
