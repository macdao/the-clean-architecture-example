package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.infrastructure.app.*;

import java.util.stream.*;

public class CreateOrderInteractor implements CreateOrderInputBoundary {
    private final OrderGateway orderGateway;
    private final TransactionManager transactionManager;
    private final OrderFactory orderFactory;
    private final CreateOrderOutputBoundary outputBoundary;

    public CreateOrderInteractor(OrderGateway orderGateway, TransactionManager transactionManager, OrderFactory orderFactory, CreateOrderOutputBoundary outputBoundary) {
        this.orderGateway = orderGateway;
        this.transactionManager = transactionManager;
        this.orderFactory = orderFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(CreateOrderInputData inputData) {
        var order = orderFactory.create(inputData.getItems(), inputData.getAddressProvince(), inputData.getAddressCity(), inputData.getAddressDetail());
        transactionManager.startTransaction();
        try {
            orderGateway.insert(order);
            transactionManager.commit();

            var orderItemDataList = order.getItems().stream()
                    .map(orderItem -> new OrderItemData(orderItem.getProductId().toString(), orderItem.getCount(), orderItem.getItemPrice()))
                    .collect(Collectors.toList());
            var address = order.getAddress();
            var createOrderOutputData = new CreateOrderOutputData(order.getId().toString(), orderItemDataList, address.getProvince(), address.getCity(), address.getDetail());

            outputBoundary.succeedWith(createOrderOutputData);
        } catch (Exception e) {
            outputBoundary.failWith(e);
        }
    }
}
