package com.macdao.ecommerce.order.details;

import com.macdao.ecommerce.order.entities.*;
import com.macdao.ecommerce.order.usecases.*;

public class UuidOrderIdGenerator implements OrderIdGenerator {
    private int currentValue = 0;

    @Override
    public OrderId generate() {
        return new OrderId(String.valueOf(++currentValue));
    }
}
