package com.codecool.shop.model;

public class Order {

    private int id;
    private final Cart cart;
    private final CustomerData customerData;
    private String lastFourDigitsOfCardNumber;

    public Order(Cart cart, CustomerData customerData) {
        if (cart == null || customerData == null) {
            throw new IllegalArgumentException("argument was null");
        }
        this.cart = cart;
        this.customerData = customerData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastFourDigitsOfCardNumber(String lastFourDigitsOfCardNumber) {
        this.lastFourDigitsOfCardNumber = lastFourDigitsOfCardNumber;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public Cart getCart() {
        return cart;
    }
}
