package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;

import java.math.*;

import static java.util.List.*;

public class CreateOrderController {
    private final CreateOrderInputBoundary createOrder;

    public CreateOrderController(CreateOrderInputBoundary createOrder) {
        this.createOrder = createOrder;
    }

    public void execute(String input) {
        var split = input.split("\\s");
        var productId = split[1];
        var count = Integer.parseInt(split[2]);
        var itemPrice = new BigDecimal(split[3]);
        var addressProvince = split[4];
        var addressCity = split[5];
        var addressDetail = split[6];

        var inputData = new CreateOrderInputData(of(new OrderItemData(productId, count, itemPrice)), addressProvince, addressCity, addressDetail);

        createOrder.execute(inputData);
    }
}
