package com.macdao.ecommerce.order.details.database;

import com.macdao.ecommerce.order.entities.*;
import com.macdao.ecommerce.order.usecases.*;

import java.util.*;

public class InMemOrderGateway implements OrderGateway {
    private final Map<OrderId, Order> orders = new HashMap<>();

    @Override
    public Optional<Order> getOrder(OrderId id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public void insert(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public void update(Order order) {
        orders.put(order.getId(), order);
    }
}
