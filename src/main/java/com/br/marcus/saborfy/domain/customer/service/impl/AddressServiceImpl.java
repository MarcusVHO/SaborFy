package com.br.marcus.saborfy.domain.customer.service.impl;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.CustomerAddressRepository;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.domain.customer.service.AddressService;
import com.br.marcus.saborfy.exceptions.AddressNotFoundException;
import com.br.marcus.saborfy.exceptions.CustomerMismatchException;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import com.br.marcus.saborfy.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;

    public AddressServiceImpl(CustomerAddressRepository customerAddressRepository, CustomerRepository customerRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
    }

    public CustomerAddress create(AuthenticatedUser user, CreateAddressRequest request, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        CustomerAddress newAddress = new CustomerAddress(
                request.address(),
                request.number(),
                request.complement(),
                customer,
                user.id()
        );
        customerAddressRepository.save(newAddress);
        return newAddress;
    }

    public void delete(Long customerId, Long addressId) {
        CustomerAddress address = customerAddressRepository.findByIdAndCustomerId(addressId, customerId)
                .orElseThrow(AddressNotFoundException::new);
        customerAddressRepository.delete(address);

    }
}
