package com.macdao.ecommerce.order.adapters.web;

import java.math.*;
import java.util.*;

public class CreateOrderRequest {
    private List<OrderItem> orderItemList;
    private Address address;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class OrderItem {
        private String productId;
        private int count;
        private BigDecimal itemPrice;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public BigDecimal getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(BigDecimal itemPrice) {
            this.itemPrice = itemPrice;
        }
    }

    public static class Address {
        private String province;
        private String city;
        private String detail;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
