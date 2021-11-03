package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CustomerData;
import com.codecool.shop.model.Order;

public class OrderService {

    public Order findOrderId(String orderId) {
        return OrderDaoMem.getInstance().find(Integer.parseInt(orderId));
    }

    public Order createNewOrder(Cart cart, CustomerData customerData) {
        return new Order(cart, customerData);
    }

    public void addNewOrderToDatabase(Order order) {
        OrderDao orderDao = OrderDaoMem.getInstance();
        orderDao.add(order);
    }

}
