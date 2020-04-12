package com.macdao.ecommerce.order.adapters.web;

public class CreateOrderViewModel {
    private final String orderId;
    private final String items;
    private final String address;

    public CreateOrderViewModel(String orderId, String items, String address) {
        this.orderId = orderId;
        this.items = items;
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }
}
