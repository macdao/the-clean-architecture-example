package com.macdao.ecommerce.order.entities;

public class Address {
    private final String province;
    private final String city;
    private final String detail;

    public Address(String province, String city, String detail) {
        this.province = province;
        this.city = city;
        this.detail = detail;
    }

    public Address changeDetailTo(String detail) {
        return new Address(this.province, this.city, detail);
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDetail() {
        return detail;
    }
}
