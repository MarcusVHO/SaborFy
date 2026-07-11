package com.br.marcus.saborfy.domain.customer.mapper;

import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {
    CustomerResponseDTO toResponse(Customer customer);
    List<CustomerResponseDTO> toResponseList(List<Customer> customers);
}
