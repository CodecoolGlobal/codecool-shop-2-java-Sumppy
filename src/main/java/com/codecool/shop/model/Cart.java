package com.codecool.shop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart = new HashMap<>();
    private static Cart instance = null;
    private BigDecimal valueOfCart = new BigDecimal(0);

    private static final Logger logger = LoggerFactory.getLogger(Cart.class);

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void updateProduct(Product product, int newQuantity) {
        int oldQuantity = cart.getOrDefault(product, 0);
        if (newQuantity==0) {
            deleteProduct(product);
        } else {
            cart.put(product, newQuantity);
            calculateCartValue();
        }
        logger.debug("Number of '{}' set to {}, old value was: {} ", product.getName(), cart.get(product), oldQuantity);
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    private void calculateCartValue() {
        BigDecimal valueSum = new BigDecimal(0);
        for (Product product : cart.keySet()) {
            valueSum = valueSum.add(product.getDefaultPrice().multiply(new BigDecimal(cart.get(product))));
        }
        BigDecimal oldCartValue = valueOfCart;
        valueOfCart = valueSum;
        logger.debug("Cart value set to : {}, old value: {}", valueOfCart, oldCartValue);
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
