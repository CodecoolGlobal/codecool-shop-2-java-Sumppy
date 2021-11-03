package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CustomerData;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment/credit-card"})
public class PaymentController extends HttpServlet {

    private final Cart cart = Cart.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerData customerData = getData(request);
        OrderService orderService = new OrderService();
        Order order = orderService.createNewOrder(cart, customerData);
        orderService.addNewOrderToDatabase(order);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("customerData", customerData);
        context.setVariable("orderId", order.getId());
        engine.process("creditCardPayment.html", context, response.getWriter());

    }

    private CustomerData getData(HttpServletRequest req) {
        String name = req.getParameter("firstname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Address billing = getAddress(req, "billing");
        Address shipping = getAddress(req, "billing");
        return new CustomerData(name, email, phone, billing, shipping);
    }

    private Address getAddress(HttpServletRequest req, String addressType) {
        String country = req.getParameter(addressType + "-country");
        String city = req.getParameter(addressType + "-city");
        String zipCode = req.getParameter(addressType + "-zip-code");
        String address = req.getParameter(addressType + "-address");
        return new Address(country, city, zipCode, address);
    }
}
