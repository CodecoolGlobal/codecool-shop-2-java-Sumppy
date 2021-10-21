package com.codecool.shop.model;

public class Order {

    private int id;
    private final Cart cart;
    private final CustomerData customerData;
    private String lastFourDigitsOfCardNumber;

    public Order(Cart cart, CustomerData customerData) {
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
}
