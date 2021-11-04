package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CustomerData;
import com.codecool.shop.model.Order;

public class OrderService {

    OrderDaoMem orderDaoMem;

    public OrderService(OrderDaoMem orderDaoMem) {
        this.orderDaoMem = orderDaoMem;
    }

    public Order findOrderById(String orderId) {
        return orderDaoMem.find(Integer.parseInt(orderId));
    }

    public Order createNewOrder(Cart cart, CustomerData customerData) {
        return new Order(cart, customerData);
    }

    public void addNewOrderToDatabase(Order order) {
        orderDaoMem.add(order);
    }

}
