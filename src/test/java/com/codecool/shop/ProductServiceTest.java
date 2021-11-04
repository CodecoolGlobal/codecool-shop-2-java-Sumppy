package com.codecool.shop;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private final ProductDao productDaoMock = Mockito.mock(ProductDao.class);
    private final ProductCategoryDao productCategoryDaoMock = Mockito.mock(ProductCategoryDao.class);
    private final SupplierDao supplierDaoMock = Mockito.mock(SupplierDao.class);
    private final ProductService productService = new ProductService(productDaoMock, productCategoryDaoMock, supplierDaoMock);

    ProductCategory category;
    List<Product> products;
    Supplier supplier;
    Product product;

    @BeforeEach
    void setUp() {
        category = new ProductCategory("A", "B", "C");
        products = new ArrayList<>();
        supplier = new Supplier("S", "D");
        product = new Product("A", new BigDecimal(1), "", "", category, supplier);
        products.add(product);
    }

    @Test
    void testGetProductsForCategory() {
//        products.add(new Product("A", new BigDecimal(1), "", "", category, supplier));
        when(productCategoryDaoMock.find(1)).thenReturn(category);
        when(productDaoMock.getBy(category)).thenReturn(products);
        assertEquals(products, productService.getProductsForCategory(1));
    }

    @Test
    void testGetProductsForCategoryNonExistingId() {
        List<Product> products = new ArrayList<>();
        assertEquals(products, productService.getProductsForCategory(11111));
    }

    @Test
    void testGetProductsForSupplier() {
        when(supplierDaoMock.find(1)).thenReturn(supplier);
        when(productDaoMock.getBy(supplier)).thenReturn(products);
        assertEquals(products, productService.getProductsForSupplier(1));
    }

    @Test
    void testGetProductsForSupplierWrongArgument() {
        when(supplierDaoMock.find(1)).thenReturn(supplier);
        when(productDaoMock.getBy(supplier)).thenReturn(products);
        assertEquals(new ArrayList<>(), productService.getProductsForSupplier(2));
    }

    @Test
    void testFindProductById() {
        when(productDaoMock.find(1)).thenReturn(product);
        assertEquals(product, productService.findProductById(1));
    }

    @Test
    void testFindProductByWrongId() {
        when(productDaoMock.find(1)).thenReturn(product);
        assertNull(productService.findProductById(2));
    }
}
