package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.CustomerData;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerData customerData = getData(request);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("customerData", customerData);
        engine.process("creditCardPayment.html", context, response.getWriter());

    }

    private Address getAddress(HttpServletRequest req, String addressType) {
        String country = req.getParameter(addressType + "-country");
        String city = req.getParameter(addressType + "-city");
        String zipCode = req.getParameter(addressType + "-zip-code");
        String address = req.getParameter(addressType + "-address");
        return new Address(country, city, zipCode, address);
    }

    private CustomerData getData(HttpServletRequest req) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Address billing = getAddress(req, "billing");
        Address shipping = getAddress(req, "shipping");
        return new CustomerData(name, email, phone, billing, shipping);
    }
}
