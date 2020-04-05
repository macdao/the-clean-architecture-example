package com.macdao.ecommerce.order.interfaceadapters.console;

import com.macdao.ecommerce.order.usecases.*;

public class ChangeAddressDetailController {
    private final ChangeAddressDetailInputBoundary changeAddressDetail;

    public ChangeAddressDetailController(ChangeAddressDetailInputBoundary changeAddressDetail) {
        this.changeAddressDetail = changeAddressDetail;
    }

    public void execute(String input) {
        var split = input.split("\\s");
        var orderId = split[1];
        var detail = split[2];

        var inputData = new ChangeAddressDetailInputData(orderId, detail);

        changeAddressDetail.execute(inputData);
    }
}
