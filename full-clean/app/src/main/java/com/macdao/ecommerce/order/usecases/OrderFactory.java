package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.order.entities.*;

import java.util.*;
import java.util.stream.*;

public class OrderFactory {
    private final OrderIdGenerator idGenerator;

    public OrderFactory(OrderIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order create(List<OrderItemData> items, String addressProvince, String addressCity, String addressDetail) {
        var orderId = idGenerator.generate();
        var orderItems = items.stream()
                .map(orderItem -> new OrderItem(new ProductId(orderItem.getProductId()), orderItem.getCount(), orderItem.getItemPrice()))
                .collect(Collectors.toList());
        return Order.create(orderId, orderItems, new Address(addressProvince, addressCity, addressDetail));
    }
}
