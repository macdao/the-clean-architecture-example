package com.macdao.ecommerce.order.interfaceadapters.web;

import com.macdao.ecommerce.order.usecases.*;

public class ChangeAddressDetailPresenter implements ChangeAddressDetailOutputBoundary {
    private Exception exception;

    @Override
    public void succeedWith(ChangeAddressDetailOutputData detail) {
    }

    @Override
    public void failWith(Exception exception) {
        this.exception = exception;
    }

    public boolean isSuccessful() {
        return exception == null;
    }

    public Exception getException() {
        return exception;
    }
}
