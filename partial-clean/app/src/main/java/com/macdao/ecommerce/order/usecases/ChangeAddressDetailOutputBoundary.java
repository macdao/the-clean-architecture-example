package com.macdao.ecommerce.order.usecases;

public interface ChangeAddressDetailOutputBoundary {
    void succeedWith(ChangeAddressDetailOutputData detail);

    void failWith(Exception exception);
}
