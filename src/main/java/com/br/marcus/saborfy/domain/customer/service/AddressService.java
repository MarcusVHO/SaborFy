package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.AddressDTO;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;


public interface AddressService {
    AddressDTO create(AuthenticatedUser user, CreateAddressRequest request, Long customerId);
    void delete(Long customerId, Long addressId);
}
