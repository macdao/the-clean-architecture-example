package com.macdao.ecommerce.order.usecases;

public interface CreateOrderOutputBoundary {
    void succeedWith(CreateOrderOutputData createOrderOutputData);

    void failWith(Exception exception);
}
