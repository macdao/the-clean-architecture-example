package com.macdao.ecommerce.order.adapters.web;

import com.macdao.ecommerce.order.usecases.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;

import static java.lang.String.*;
import static org.springframework.web.context.WebApplicationContext.*;

@RestController
@RequestMapping("/orders")
@Scope(SCOPE_REQUEST)
public class OrderController {
    private final CreateOrderInteractor createOrder;
    private final ChangeAddressDetailInteractor changeAddressDetail;

    public OrderController(CreateOrderInteractor createOrder, ChangeAddressDetailInteractor changeAddressDetail) {
        this.createOrder = createOrder;
        this.changeAddressDetail = changeAddressDetail;
    }

    @PostMapping
    public CreateOrderViewModel createOrder(@RequestBody CreateOrderRequest request) {
        if (request.getOrderItemList().isEmpty()) {
            throw new IllegalArgumentException("orderItemList should not be empty");
        }

        var inputData = toCreateOrderInputData(request);

        var outputData = createOrder.execute(inputData);

        return toCreateOrderViewModel(outputData);
    }

    @PutMapping("/{orderId}/address/detail")
    public void changeAddressDetail(@PathVariable String orderId, @RequestBody String detail) {
        var inputData = new ChangeAddressDetailInputData(orderId, detail);

        changeAddressDetail.execute(inputData);
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
