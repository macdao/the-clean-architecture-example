package com.macdao.ecommerce.order.interfaceadapters.web;

import com.macdao.ecommerce.order.usecases.*;

import java.util.*;

import static java.lang.String.*;

public class CreateOrderPresenter implements CreateOrderOutputBoundary {
    private CreateOrderOutputData outputData;
    private Exception exception;

    @Override
    public void succeedWith(CreateOrderOutputData createOrderOutputData) {
        this.outputData = createOrderOutputData;
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

    public CreateOrderViewModel toViewModel() {
        var items = Arrays.toString(outputData.getOrderItemDataList().stream()
                .map(orderItem -> format("%s %s %s", orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                .toArray());
        var address = format("%s %s %s", outputData.getAddressProvince(), outputData.getAddressCity(), outputData.getAddressDetail());
        return new CreateOrderViewModel(outputData.getOrderId(), items, address);
    }
}
