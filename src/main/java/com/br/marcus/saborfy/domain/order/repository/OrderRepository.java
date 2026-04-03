package com.br.marcus.saborfy.domain.order.repository;

import com.br.marcus.saborfy.domain.order.entity.Order;
import com.br.marcus.saborfy.domain.order.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
        SELECT o FROM Order o
        WHERE (:name IS NULL OR LOWER(o.customer.customerName) LIKE (CONCAT('%', :name, '%')))\s
        AND (:status IS NULL OR o.orderStatus = :status)
        AND (:orderNumber IS NULL OR o.id = :orderNumber)
        AND (:street IS NULL OR LOWER(o.address.street) LIKE CONCAT('%', :street, '%') )
        AND (:addressNumber IS NULL OR o.address.number =:addressNumber)
        AND (:startDate IS NULL OR o.createdAt >= :startDate)
        AND (:endDate IS NULL OR o.createdAt <= :endDate)
""")
    List<Order> findOrders(
            @Param("name") String name,
            @Param("status") OrderStatus status,
            @Param("orderNumber") Long orderNumber,
            @Param("street") String street,
            @Param("addressNumber") Integer addressNumber,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate );
}
