package com.br.marcus.saborfy.domain.menu.mapper;

import com.br.marcus.saborfy.domain.menu.dto.response.MenuResponse;
import com.br.marcus.saborfy.domain.menu.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MenuMapper {
    MenuResponse toResponse(Menu menu);
    List<MenuResponse> toResponseList(List<Menu> menuList);
}
