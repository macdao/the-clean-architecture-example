package com.macdao.ecommerce.order.usecases;

import java.util.*;

public class CreateOrderInputData {
    private final List<OrderItemData> items;
    private final String addressProvince;
    private final String addressCity;
    private final String addressDetail;

    public CreateOrderInputData(List<OrderItemData> items, String addressProvince, String addressCity, String addressDetail) {
        this.items = items;
        this.addressProvince = addressProvince;
        this.addressCity = addressCity;
        this.addressDetail = addressDetail;
    }

    public List<OrderItemData> getItems() {
        return items;
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
