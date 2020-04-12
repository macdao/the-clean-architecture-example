package com.macdao.ecommerce.order.usecases;

import java.util.*;

public class CreateOrderOutputData {
    private final String orderId;
    private final List<OrderItemData> orderItemDataList;
    private final String addressProvince;
    private final String addressCity;
    private final String addressDetail;

    public CreateOrderOutputData(String orderId, List<OrderItemData> orderItemDataList, String addressProvince, String addressCity, String addressDetail) {
        this.orderId = orderId;
        this.orderItemDataList = orderItemDataList;
        this.addressProvince = addressProvince;
        this.addressCity = addressCity;
        this.addressDetail = addressDetail;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItemData> getOrderItemDataList() {
        return orderItemDataList;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressDetail() {
        return addressDetail;
    }
}
