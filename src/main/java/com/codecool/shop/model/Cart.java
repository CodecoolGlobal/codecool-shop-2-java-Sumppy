package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart = new HashMap<>();
    private static Cart instance = null;
    private BigDecimal valueOfCart = null;

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void updateProduct(Product product, int newQuantity) {
        if (newQuantity==0) {
            deleteProduct(product);
        } else {
            cart.put(product, newQuantity);
            calculateCartValue();
        }
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    private void calculateCartValue() {
        for (Product product : cart.keySet()) {
            valueOfCart = valueOfCart.add(product.getDefaultPrice().multiply(new BigDecimal(cart.get(product))));
        }
    }

    public BigDecimal getValueOfCart() {
        return valueOfCart;
    }

    public void deleteProduct(Product product) {
        cart.remove(product);
    }
}
