package com.codecool.shop.queries;

public class Queries {
    public static String addProduct(){
        return "INSERT INTO products (category_id, supplier_id, name, description, price, currency) VALUES (?, ?, ?, ?, ?, ?)";
    }

    public static String getProductById(){
        return "SELECT * FROM products WHERE id = ?";
    }

    public static String getProductsBySupplier(){
        return "SELECT * FROM products WHERE supplier_id = ?";
    }

    public static String getProductsByCategory(){
        return "SELECT * FROM products WHERE category_id = ?";
    }

    public static String removeProduct(){
        return "DELETE FROM products WHERE id = ?";
    }

    public static String getAllProducts(){
        return "SELECT * FROM products";
    }

    public static String addCategory(){
        return "INSERT INTO categories (name, department, description) VALUES (?, ?, ?)";
    }

    public static String getAllCategories(){
        return "SELECT * FROM categories";
    }

    public static String getCategory(){
        return "SELECT * FROM categories WHERE id = ?";
    }

    public static String removeCategory(){
        return "DELETE FROM categories WHERE id = ?";
    }

    public static String addSupplier(){
        return "INSERT INTO suppliers (name, description) VALUES(?, ?)";
    }

    public static String getAllSuppliers(){
        return "SELECT * FROM suppliers";
    }

    public static String getSupplier(){
        return "SELECT * FROM suppliers WHERE id = ?";
    }

    public static String removeSupplier(){
        return "DELETE FROM suppliers WHERE id = ?";
    }
}
