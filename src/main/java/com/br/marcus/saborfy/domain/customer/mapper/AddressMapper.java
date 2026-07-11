package com.br.marcus.saborfy.domain.customer.mapper;

import com.br.marcus.saborfy.domain.customer.dto.response.AddressDTO;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {
    AddressDTO toResponse(CustomerAddress address);
}
