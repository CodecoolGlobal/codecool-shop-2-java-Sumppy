package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns ={"/filter-products"})
public class FilterController extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        PrintWriter out = response.getWriter();

        var productsByCategory = getProductsByFilter(request.getParameter("filter"), Integer.parseInt(request.getParameter("id")), productService);
        String productsJsonString = this.gson.toJson(productsByCategory);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(productsJsonString);
        out.flush();
    }

    private List<Product> getProductsByFilter(String filer, int id, ProductService productService) {
        List<Product> products = new ArrayList<>();
        switch (filer) {
            case "category":
                products =  productService.getProductsForCategory(id);
                break;
            case "supplier":
                products = productService.getProductsForSupplier(id);
                break;
        }
        return products;
    }

}
