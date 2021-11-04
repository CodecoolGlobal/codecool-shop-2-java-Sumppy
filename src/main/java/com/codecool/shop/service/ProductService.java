package com.codecool.shop.service;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.db.DatabaseManager;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService() {
        if(Initializer.isDatabaseAvailable){
            DatabaseManager databaseManager = new DatabaseManager();
            this.productDao = databaseManager.getProductDaoDb();
            this.productCategoryDao = databaseManager.getProductCategoryDaoDb();
            this.supplierDao = databaseManager.getSupplierDaoDb();
        }
    }

    /*public ProductService(ProductDao productDao) {
            this.productDao = productDao;
        }

        public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
            this.productDao = productDao;
            this.productCategoryDao = productCategoryDao;
        }

        public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
            this.productDao = productDao;
            this.productCategoryDao = productCategoryDao;
            this.supplierDao = supplierDao;
        }
    */
    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = supplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public Product findProductById(int productId) {
        return productDao.find(productId);
    }

    public List<ProductCategory> getAllCategories(){
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<Product> getAllProduct() {
        return productDao.getAll();
    }
}
