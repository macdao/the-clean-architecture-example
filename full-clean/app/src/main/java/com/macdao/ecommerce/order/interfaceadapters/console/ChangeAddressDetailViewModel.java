package com.macdao.ecommerce.order.interfaceadapters.console;

public class ChangeAddressDetailViewModel {
    private final boolean successful;
    private final String orderId;
    private final String detail;
    private final String reason;

    public ChangeAddressDetailViewModel(boolean successful, String orderId, String detail, String reason) {
        this.successful = successful;
        this.orderId = orderId;
        this.detail = detail;
        this.reason = reason;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDetail() {
        return detail;
    }

    public String getReason() {
        return reason;
    }
}
