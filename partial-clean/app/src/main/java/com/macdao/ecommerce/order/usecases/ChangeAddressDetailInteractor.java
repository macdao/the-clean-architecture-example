package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.infrastructure.app.*;
import com.macdao.ecommerce.order.entities.*;

public class ChangeAddressDetailInteractor {
    private final OrderGateway orderGateway;
    private final TransactionManager transactionManager;

    public ChangeAddressDetailInteractor(OrderGateway orderGateway, TransactionManager transactionManager) {
        this.orderGateway = orderGateway;
        this.transactionManager = transactionManager;
    }

    public ChangeAddressDetailOutputData execute(ChangeAddressDetailInputData inputData) {
        var orderId = inputData.getOrderId();

        try {
            transactionManager.startTransaction();
            return orderGateway.getOrder(new OrderId(orderId))
                    .map(order -> {
                        order.changeAddressDetail(inputData.getDetail());
                        orderGateway.update(order);
                        transactionManager.commit();
                        return new ChangeAddressDetailOutputData(orderId, inputData.getDetail());
                    })
                    .orElseThrow(() -> {
                        transactionManager.rollback();
                        return new OrderNotFoundException(orderId);
                    });
        } catch (Exception e) {
            transactionManager.rollback();
            throw e;
        }
    }
}
