package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart = new HashMap<>();
    private static Cart instance = null;
    private BigDecimal valueOfCart = new BigDecimal(0);

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
        BigDecimal valueSum = new BigDecimal(0);
        for (Product product : cart.keySet()) {
            valueSum = valueSum.add(product.getDefaultPrice().multiply(new BigDecimal(cart.get(product))));
        }
        valueOfCart = valueSum;
    }

    public BigDecimal getValueOfCart() {
        return valueOfCart;
    }

    public String getCartItems() {
        StringBuilder sb = new StringBuilder();
        for (Product product : cart.keySet()) {
            sb.append(cart.get(product)+" ").append("number of ").append(product.name).append("\n");
        }
        return sb.toString();
    }

    public void deleteProduct(Product product) {
        cart.remove(product);
    }
}
