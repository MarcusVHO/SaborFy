package com.br.marcus.saborfy.domain.customer.dto.response;

import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public record CustomerResponseDTO (
    long id,
    String name,
    List<PhoneDTO> phones,
    List<AddressDTO> addresses
    ){

}
