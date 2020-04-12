package com.macdao.ecommerce.order.usecases;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.stream.*;

@Service
public class CreateOrderInteractor {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public CreateOrderInteractor(OrderRepository orderRepository, OrderFactory orderFactory) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    @Transactional
    public CreateOrderOutputData execute(CreateOrderInputData inputData) {
        var order = orderFactory.create(inputData.getItems(), inputData.getAddressProvince(), inputData.getAddressCity(), inputData.getAddressDetail());
        orderRepository.save(order);

        var orderItemDataList = order.getItems().stream()
                .map(orderItem -> new OrderItemData(orderItem.getProductId().toString(), orderItem.getCount(), orderItem.getItemPrice()))
                .collect(Collectors.toList());
        var address = order.getAddress();

        return new CreateOrderOutputData(order.getId().toString(), orderItemDataList, address.getProvince(), address.getCity(), address.getDetail());
    }
}
