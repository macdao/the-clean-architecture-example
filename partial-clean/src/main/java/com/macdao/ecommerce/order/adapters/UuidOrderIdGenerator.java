package com.macdao.ecommerce.order.adapters;

import com.macdao.ecommerce.order.entities.*;
import com.macdao.ecommerce.order.usecases.*;
import org.springframework.stereotype.*;

@Component
public class UuidOrderIdGenerator implements OrderIdGenerator {
    private int currentValue = 0;

    @Override
    public OrderId generate() {
        return new OrderId(String.valueOf(++currentValue));
    }
}
