package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.CustomerAddressRepository;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.AddressNotFoundException;
import com.br.marcus.saborfy.exceptions.CustomerMismatchException;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import com.br.marcus.saborfy.domain.user.entity.User;
import org.springframework.stereotype.Service;


@Service
public class CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository, CustomerRepository customerRepository) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerRepository = customerRepository;
    }

    public CustomerAddress create(AuthenticatedUser user, CreateAddressRequest request, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        User currentUser = new User();
        currentUser.setId(user.id());


        CustomerAddress newAddress = new CustomerAddress();
        newAddress.setAddress(request.address());
        newAddress.setNumber(request.number());
        newAddress.setComplement(request.complement());
        newAddress.setCustomer(customer);
        newAddress.setUser(currentUser);

        customerAddressRepository.save(newAddress);
        return newAddress;
    }

    public void delete(Long customerId, Long addressId) {
        CustomerAddress address = customerAddressRepository.findById(addressId)
                .orElseThrow(AddressNotFoundException::new);

        if (!address.getCustomer().getId().equals(customerId)) {
            throw new CustomerMismatchException("Address does not belong to this customer");
        }

        customerAddressRepository.delete(address);

    }
}
