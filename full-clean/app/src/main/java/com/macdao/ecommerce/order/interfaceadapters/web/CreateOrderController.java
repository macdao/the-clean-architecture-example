package com.macdao.ecommerce.order.interfaceadapters.web;

import com.macdao.ecommerce.order.usecases.*;

import java.util.stream.*;

public class CreateOrderController {
    private final CreateOrderInputBoundary createOrder;

    public CreateOrderController(CreateOrderInputBoundary createOrder) {
        this.createOrder = createOrder;
    }

    public void execute(CreateOrderRequest request) {
        if (request.getOrderItemList().isEmpty()) {
            throw new IllegalArgumentException("orderItemList should not be empty");
        }
        var orderItemList = request.getOrderItemList().stream()
                .map(orderItem -> new OrderItemData(orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                .collect(Collectors.toList());
        var address = request.getAddress();
        var inputData = new CreateOrderInputData(orderItemList, address.getProvince(), address.getCity(), address.getDetail());

        createOrder.execute(inputData);
    }
}
