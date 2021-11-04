package com.codecool.shop;

import static org.junit.jupiter.api.Assertions.*;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CustomerData;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;
import org.junit.jupiter.api.*;

public class OrderServiceTest {

    private final OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
    private final OrderService orderService = new OrderService(orderDaoMem);
    Order order;
    Cart cart;
    Address address;
    CustomerData customerData;

    @BeforeEach
    void doBefore() {
        this.cart = Cart.getInstance();
        this.address = new Address("H", "BP", "1", "");
        this.customerData = new CustomerData("Peti", "", "", address, address);
        this.order = new Order(cart, customerData);

    }

    @AfterEach
    void doAfter() {
        this.orderDaoMem.remove(1);
    }

    @Test
    void testFindOrderById() {
        this.orderService.addNewOrderToDatabase(order);
        assertEquals(order, orderService.findOrderById("1"));
    }

    @Test
    void testFindOrderByIdWrongArgument() {
        assertThrows(NumberFormatException.class, () -> orderService.findOrderById(null));
    }

    @Test
    void testCreateNewOrderIsInstanceOfOrder() {
        Order newOrder = orderService.createNewOrder(cart, customerData);
        assertInstanceOf(Order.class, newOrder);
    }

    @Test
    void testCreateNewOrderWrongArguments() {
        assertThrows(IllegalArgumentException.class, () -> orderService.createNewOrder(null, null));
    }

    @Test
    void testAddNewOrderToDatabaseHappy() {
        assertNull(orderService.findOrderById("1"));
        orderService.addNewOrderToDatabase(order);
        assertEquals(order, orderService.findOrderById("1"));
    }

    @Test
    void testAddNewOrderToDatabaseWrongArgument() {
        assertThrows(NullPointerException.class, () -> orderService.addNewOrderToDatabase(null));
    }
}
