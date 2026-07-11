package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;

import java.util.List;

public interface PhoneService {
    CustomerPhone create(AuthenticatedUser user, Long customerId, CreatePhoneRequest request);
    void delete(Long customerId, Long phoneId);
}
