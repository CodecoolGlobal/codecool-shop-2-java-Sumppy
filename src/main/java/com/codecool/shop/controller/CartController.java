package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ={"/cart"})
public class CartController extends HttpServlet {

    private Cart cart = Cart.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductService productService = new ProductService();
        int quantityOfProduct = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(productId);
        cart.updateProduct(product, quantityOfProduct);
    }
}
