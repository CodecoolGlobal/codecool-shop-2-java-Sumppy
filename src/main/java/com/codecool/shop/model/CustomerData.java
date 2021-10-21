package com.codecool.shop.model;

public class CustomerData {

    private String name;
    private String email;
    private String phone;

    private Address billingAddress;
    private Address shippingAddress;

    public CustomerData(String name, String email, String phone, Address billingAddress, Address shippingAddress) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }
}
