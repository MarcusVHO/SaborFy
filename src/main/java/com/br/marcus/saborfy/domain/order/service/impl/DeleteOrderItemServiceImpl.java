package com.br.marcus.saborfy.domain.order.service.impl;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.entity.OrderItem;
import com.br.marcus.saborfy.domain.order.repository.OrderRepository;
import com.br.marcus.saborfy.domain.order.service.DeleteOrderItemService;
import com.br.marcus.saborfy.exceptions.ItemNotFoundException;
import com.br.marcus.saborfy.exceptions.OrderNotFoundException;
import com.br.marcus.saborfy.exceptions.OrderWithFewItemsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeleteOrderItemServiceImpl implements DeleteOrderItemService {
    private final OrderRepository orderRepository;

    public DeleteOrderItemServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void deleteOrderItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems.size() == 1) {
            throw new OrderWithFewItemsException();
        }
        orderItems.remove(orderItems.stream().filter(p -> Objects.equals(p.getId(), itemId)).findFirst().orElseThrow(ItemNotFoundException::new));
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }
}
