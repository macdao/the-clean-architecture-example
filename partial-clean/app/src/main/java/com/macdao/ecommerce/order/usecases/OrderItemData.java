package com.macdao.ecommerce.order.usecases;

import java.math.*;

public class OrderItemData {
    private final String productId;
    private final int count;
    private final BigDecimal itemPrice;

    public OrderItemData(String productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}
