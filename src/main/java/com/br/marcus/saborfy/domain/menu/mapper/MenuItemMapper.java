package com.br.marcus.saborfy.domain.menu.mapper;

import com.br.marcus.saborfy.domain.menu.dto.response.ItemMenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MenuItemMapper {
    ItemMenuResponse ToResponse(ItemMenu item);
}
