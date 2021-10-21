package com.codecool.shop.model;

public class Order {

    private  int id;
    private final Cart cart;
    private final CustomerData customerData;

    public Order(Cart cart, CustomerData customerData) {
        this.cart = cart;
        this.customerData = customerData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
