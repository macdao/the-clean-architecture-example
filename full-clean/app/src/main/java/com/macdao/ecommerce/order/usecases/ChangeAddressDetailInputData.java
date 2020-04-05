package com.macdao.ecommerce.order.usecases;

public class ChangeAddressDetailInputData {
    private final String orderId;
    private final String detail;

    public ChangeAddressDetailInputData(String orderId, String detail) {
        this.orderId = orderId;
        this.detail = detail;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDetail() {
        return detail;
    }
}
