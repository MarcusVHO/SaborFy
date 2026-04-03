package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.menu.entity.ItemMenu;
import com.br.marcus.saborfy.domain.menu.repository.ItemMenuRepository;
import com.br.marcus.saborfy.domain.order.dto.request.CreateOrderItemRequest;
import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.domain.order.service.OrderItemService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final ItemMenuRepository itemMenuRepository;


    public OrderItemServiceImpl(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    @Override
    public List<OrderItem> create(@NonNull List<CreateOrderItemRequest> items, Order order, AuthenticatedUser user) {
        List<ItemMenu> menuItems = itemMenuRepository.findAllById(items.stream().map(CreateOrderItemRequest::id).toList());
        List<OrderItem> orderItems = new ArrayList<>();
        int cont = 0;
        for (ItemMenu itemMenu : menuItems) {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setOrder(order);
            newOrderItem.setQuantity(items.get(cont).quantity());
            newOrderItem.setProductName(itemMenu.getName());
            newOrderItem.setProductDescription(itemMenu.getDescription());
            newOrderItem.setUnitPrice(itemMenu.getPrice());
            newOrderItem.setUserId(user.id());
            cont += 1;
            orderItems.add(newOrderItem);
        }
        return orderItems;
    }
}
