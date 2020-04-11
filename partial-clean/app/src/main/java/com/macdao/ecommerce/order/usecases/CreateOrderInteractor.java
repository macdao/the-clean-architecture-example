package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.infrastructure.app.*;

import java.util.stream.*;

public class CreateOrderInteractor {
    private final OrderGateway orderGateway;
    private final TransactionManager transactionManager;
    private final OrderFactory orderFactory;

    public CreateOrderInteractor(OrderGateway orderGateway, TransactionManager transactionManager, OrderFactory orderFactory) {
        this.orderGateway = orderGateway;
        this.transactionManager = transactionManager;
        this.orderFactory = orderFactory;
    }

    public CreateOrderOutputData execute(CreateOrderInputData inputData) {
        var order = orderFactory.create(inputData.getItems(), inputData.getAddressProvince(), inputData.getAddressCity(), inputData.getAddressDetail());
        transactionManager.startTransaction();
        try {
            orderGateway.insert(order);
            transactionManager.commit();

            var orderItemDataList = order.getItems().stream()
                    .map(orderItem -> new OrderItemData(orderItem.getProductId().toString(), orderItem.getCount(), orderItem.getItemPrice()))
                    .collect(Collectors.toList());
            var address = order.getAddress();

            return new CreateOrderOutputData(order.getId().toString(), orderItemDataList, address.getProvince(), address.getCity(), address.getDetail());
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
