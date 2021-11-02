package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Email;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(urlPatterns = {"/order-confirmation"})
public class ConfirmationController extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        sendEmail(orderId);
        Order order = OrderDaoMem.getInstance().find(Integer.parseInt(orderId));
        writeOrderIntoFile(order);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String message = "Your order is confirmed. You can refer your order with your order number: " + order.getId();
        context.setVariable("message", message);
        engine.process("confirmation.html", context, resp.getWriter());
    }

    private void sendEmail(String orderId) {
        OrderDao orderDao = OrderDaoMem.getInstance();
        Order order = orderDao.find(Integer.parseInt(orderId));
        String toMail = order.getCustomerData().getEmail();
        StringBuilder message = new StringBuilder();
        message.append("Dear ").append(order.getCustomerData().getName()).append("!\n");
        message.append("Your order with ordernumber: ").append(order.getId()).append(" has been confirmed.\n");
        message.append("It contains ").append(order.getCart().getCartItems()).append(".\n");
        message.append("Thank you for your purchase!");
        Email.send("kristof.kelle@gmail.com", System.getenv("PASSWORD"), toMail,"Successful Order",message.toString());
    }

    private void writeOrderIntoFile(Order order) throws IOException {
        String json = gson.toJson(order);
        FileWriter writer = new FileWriter("order" + order.getId()+".json");
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
