package com.macdao.ecommerce.order.interfaceadapters.web;

import com.macdao.ecommerce.order.usecases.*;

import java.util.*;
import java.util.stream.*;

import static java.lang.String.*;

public class CreateOrderController {
    private final CreateOrderInteractor createOrder;

    public CreateOrderController(CreateOrderInteractor createOrder) {
        this.createOrder = createOrder;
    }

    public CreateOrderViewModel execute(CreateOrderRequest request) {
        if (request.getOrderItemList().isEmpty()) {
            throw new IllegalArgumentException("orderItemList should not be empty");
        }

        var inputData = toCreateOrderInputData(request);

        var outputData = createOrder.execute(inputData);

        return toCreateOrderViewModel(outputData);
    }

    private CreateOrderInputData toCreateOrderInputData(CreateOrderRequest request) {
        var orderItemList = request.getOrderItemList().stream()
                .map(orderItem -> new OrderItemData(orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                .collect(Collectors.toList());
        var address = request.getAddress();
        return new CreateOrderInputData(orderItemList, address.getProvince(), address.getCity(), address.getDetail());
    }

    private CreateOrderViewModel toCreateOrderViewModel(CreateOrderOutputData outputData) {
        var items = Arrays.toString(outputData.getOrderItemDataList().stream()
                .map(orderItem -> format("%s %s %s", orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                .toArray());
        var address = format("%s %s %s", outputData.getAddressProvince(), outputData.getAddressCity(), outputData.getAddressDetail());
        return new CreateOrderViewModel(outputData.getOrderId(), items, address);
    }
}
