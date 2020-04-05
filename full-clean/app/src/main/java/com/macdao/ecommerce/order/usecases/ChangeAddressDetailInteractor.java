package com.macdao.ecommerce.order.usecases;

import com.macdao.ecommerce.infrastructure.app.*;
import com.macdao.ecommerce.order.entities.*;

public class ChangeAddressDetailInteractor implements ChangeAddressDetailInputBoundary {
    private final OrderGateway orderGateway;
    private final TransactionManager transactionManager;
    private final ChangeAddressDetailOutputBoundary outputBoundary;

    public ChangeAddressDetailInteractor(OrderGateway orderGateway, TransactionManager transactionManager, ChangeAddressDetailOutputBoundary outputBoundary) {
        this.orderGateway = orderGateway;
        this.transactionManager = transactionManager;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ChangeAddressDetailInputData inputData) {
        var orderId = inputData.getOrderId();

        try {
            transactionManager.startTransaction();
            orderGateway.getOrder(new OrderId(orderId))
                    .ifPresentOrElse(order -> {
                                order.changeAddressDetail(inputData.getDetail());
                                orderGateway.update(order);
                                transactionManager.commit();
                                outputBoundary.succeedWith(new ChangeAddressDetailOutputData(orderId, inputData.getDetail()));
                            },
                            () -> {
                                transactionManager.rollback();
                                outputBoundary.failWith(new OrderNotFoundException(orderId));
                            });
        } catch (Exception e) {
            transactionManager.rollback();
            outputBoundary.failWith(e);
        }
    }
}
