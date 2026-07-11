package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.user.entity.User;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

import java.util.List;

public interface AddressService {
    CustomerAddress create(AuthenticatedUser user, CreateAddressRequest request, Long customerId);
    void delete(Long customerId, Long addressId);
}
