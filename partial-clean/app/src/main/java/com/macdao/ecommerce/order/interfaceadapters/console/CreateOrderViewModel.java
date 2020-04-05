package com.macdao.ecommerce.order.interfaceadapters.console;

public class CreateOrderViewModel {
    private final boolean successful;
    private final String orderId;
    private final String items;
    private final String address;
    private final String reason;

    private CreateOrderViewModel(boolean successful, String orderId, String items, String address, String reason) {
        this.successful = successful;
        this.orderId = orderId;
        this.items = items;
        this.address = address;
        this.reason = reason;
    }

    public static CreateOrderViewModel succeed(String orderId, String items, String address) {
        return new CreateOrderViewModel(true, orderId, items, address, null);
    }

    public static CreateOrderViewModel fail(String reason) {
        return new CreateOrderViewModel(false, null, null, null, reason);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItems() {
        return items;
    }

    public String getAddress() {
        return address;
    }

    public String getReason() {
        return reason;
    }
}
