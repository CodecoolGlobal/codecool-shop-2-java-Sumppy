package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns ={"/cart"})
public class CartController extends HttpServlet {

    private Cart cart = Cart.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductService productService = new ProductService(productDao);
        int quantityOfProduct = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(productId);
        cart.updateProduct(product, quantityOfProduct);
    }
}
