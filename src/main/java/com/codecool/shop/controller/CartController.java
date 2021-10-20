package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/cart"})
public class CartController extends HttpServlet {

    private Cart cart = Cart.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = ProductDaoMem.getInstance();

        int quantityOfProduct = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.find(productId);
        cart.updateProduct(product, quantityOfProduct);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cartValue", cart.getValueOfCart());
        context.setVariable("products", cart.getCart());
        engine.process("product/cart.html", context, resp.getWriter());
    }
}