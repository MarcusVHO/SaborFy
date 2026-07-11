package com.br.marcus.saborfy.domain.customer.factory;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    public Customer create(CreateCustomerRequest request, AuthenticatedUser user) {
        Customer newCustomer = new Customer(request.name(), user.id());
        if (request.phones() != null) {
            for (CreatePhoneRequest requestPhone : request.phones()) {
                newCustomer.addPhone(requestPhone.number(), user.id());
            }
        }
        if (request.addresses() != null) {
            for (CreateAddressRequest addressRequest : request.addresses()) {
                newCustomer.addAddress(
                        addressRequest.address(),
                        addressRequest.number(),
                        addressRequest.complement(),
                        user.id()
                );
            }
        }
        return newCustomer;
    }
}
