package com.br.marcus.saborfy.domain.customer.query;

import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.repository.AddressRepository;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AddressFinder {
    private final AddressRepository addressRepository;

    public AddressFinder(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public CustomerAddress byId(Long id)  {
        return addressRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerAddress byIdAndCustomerId(Long id, Long customerId) {
        return addressRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
