package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.order.entities.*;

import java.util.*;

public interface OrderGateway {
    Optional<Order> getOrder(OrderId id);

    void insert(Order order);

    void update(Order order);
}
