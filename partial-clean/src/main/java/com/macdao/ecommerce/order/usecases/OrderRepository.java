package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.order.entities.*;

import java.util.*;

public interface OrderRepository {
    Optional<Order> findById(OrderId id);

    Order save(Order order);
}
