package com.macdao.ecommerce.order.entities;

public class OrderCannotBeModifiedException extends RuntimeException {
    public OrderCannotBeModifiedException(OrderId id) {
    }
}
