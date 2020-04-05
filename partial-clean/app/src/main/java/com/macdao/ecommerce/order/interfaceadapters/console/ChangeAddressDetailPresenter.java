package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;

public class ChangeAddressDetailPresenter implements ChangeAddressDetailOutputBoundary {
    private ChangeAddressDetailOutputData outputData;
    private Exception exception;

    @Override
    public void succeedWith(ChangeAddressDetailOutputData outputDate) {
        this.outputData = outputDate;
    }

    @Override
    public void failWith(Exception exception) {
        this.exception = exception;
    }

    public ChangeAddressDetailViewModel toViewModel() {
        var successful = outputData != null;
        if (successful) {
            return new ChangeAddressDetailViewModel(true, outputData.getOrderId(), outputData.getDetail(), null);
        }
        return new ChangeAddressDetailViewModel(false, null, null, exception.getMessage());
    }
}
