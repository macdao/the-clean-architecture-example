package com.macdao.ecommerce.order.entities;

import java.util.*;

public class Order {
    public enum OrderStatus {
        CREATED,
        PAID
    }

    private final OrderId id;
    private List<OrderItem> items;
    private OrderStatus status;
    private Address address;

    private Order(OrderId id, List<OrderItem> items, OrderStatus orderStatus, Address address) {
        this.id = id;
        this.items = items;
        this.status = orderStatus;
        this.address = address;
    }

    public static Order create(OrderId id, List<OrderItem> items, Address address) {
        return new Order(id, items, OrderStatus.CREATED, address);
    }

    public static Order reconstitute(OrderId id, List<OrderItem> items, OrderStatus orderStatus, Address address) {
        return new Order(id, items, orderStatus, address);
    }

    public void changeAddressDetail(String detail) {
        if (this.status == OrderStatus.PAID) {
            throw new OrderCannotBeModifiedException(this.id);
        }

        address = address.changeDetailTo(detail);
    }

    public OrderId getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }
}
