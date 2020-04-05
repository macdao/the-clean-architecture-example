package com.macdao.ecommerce.order.entities;

public class ProductId {
    private final String value;

    public ProductId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
