package com.br.marcus.saborfy.domain.customer.dto.response;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomerResponseDTO {
    private final long id;
    private final String name;
    private final List<PhoneDTO> phones;
    private final List<AddressDTO> addresses;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();

        this.phones = new ArrayList<>();

        for (CustomerPhone customerPhone : customer.getPhones()) {
            this.phones.add(new PhoneDTO(customerPhone));
        }

        this.addresses = new ArrayList<>();

        for (CustomerAddress customerAddress : customer.getAddresses()) {
            this.addresses.add(new AddressDTO(customerAddress));
        }
    }
}
