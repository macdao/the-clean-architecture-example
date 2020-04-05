package com.macdao.ecommerce.order.interfaceadapters.web;

import com.macdao.ecommerce.order.usecases.*;

public class ChangeAddressDetailController {
    private final ChangeAddressDetailInputBoundary changeAddressDetail;

    public ChangeAddressDetailController(ChangeAddressDetailInputBoundary changeAddressDetail) {
        this.changeAddressDetail = changeAddressDetail;
    }

    public void execute(String orderId, String detail) {
        var inputData = new ChangeAddressDetailInputData(orderId, detail);

        changeAddressDetail.execute(inputData);
    }
}
