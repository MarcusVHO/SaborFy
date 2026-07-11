package com.br.marcus.saborfy.domain.customer.mapper;

import com.br.marcus.saborfy.domain.customer.dto.response.PhoneDTO;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PhoneMapper {
    PhoneDTO toResponse(CustomerPhone phone);
}
