package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;

import java.util.*;

import static java.lang.String.*;

public class CreateOrderPresenter implements CreateOrderOutputBoundary {
    private CreateOrderOutputData outputData;
    private String reason;

    @Override
    public void succeedWith(CreateOrderOutputData createOrderOutputData) {
        outputData = createOrderOutputData;
        reason = null;
    }

    @Override
    public void failWith(Exception exception) {
        outputData = null;
        reason = exception.getMessage();
    }

    public CreateOrderViewModel toViewModel() {
        if (outputData != null) {
            var items = Arrays.toString(outputData.getOrderItemDataList().stream()
                    .map(orderItem -> format("%s %s %s", orderItem.getProductId(), orderItem.getCount(), orderItem.getItemPrice()))
                    .toArray());
            var address = format("%s %s %s", outputData.getAddressProvince(), outputData.getAddressCity(), outputData.getAddressDetail());
            return CreateOrderViewModel.succeed(outputData.getOrderId(), items, address);
        }
        return CreateOrderViewModel.fail(reason);
    }

}
