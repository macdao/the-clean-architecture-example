package com.macdao.ecommerce.order.entities;

import java.math.*;

public class OrderItem {
    private final ProductId productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItem(ProductId productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}
