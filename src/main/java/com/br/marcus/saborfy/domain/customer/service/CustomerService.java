package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO create (CreateCustomerRequest request, AuthenticatedUser user);
    void delete(Long customerId);
    List<CustomerResponseDTO> findCustomers(String name);
    CustomerResponseDTO update(AuthenticatedUser user, Long customerId, UpdateCustomerRequest request);
}
