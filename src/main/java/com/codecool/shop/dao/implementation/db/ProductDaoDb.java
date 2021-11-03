package com.codecool.shop.dao.implementation.db;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.queries.Queries;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDaoDb implements ProductDao {
    private DataSource dataSource;
    private ProductCategoryDaoDb productCategoryDaoDb;
    private SupplierDaoDb supplierDaoDb;

    public ProductDaoDb(DataSource dataSource, ProductCategoryDaoDb productCategoryDaoDb, SupplierDaoDb supplierDaoDb) {
        this.dataSource = dataSource;
        this.productCategoryDaoDb = productCategoryDaoDb;
        this.supplierDaoDb = supplierDaoDb;
    }

    @Override
    public void add(Product product) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.addProduct();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, product.getProductCategory().getId());
            statement.setInt(2, product.getSupplier().getId());
            statement.setString(3, product.getName());
            statement.setString(4, product.getDescription());
            statement.setInt(5, Integer.parseInt(product.getPrice()));
            statement.setString(6, product.getDefaultCurrency());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        }

    @Override
    public Product find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getProductById();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            int category_id = resultSet.getInt(2);
            int supplier_id = resultSet.getInt(3);
            String name = resultSet.getString(4);
            String description = resultSet.getString(5);
            BigDecimal price = new BigDecimal(resultSet.getInt(6));
            String currency = resultSet.getString(7);

            ProductCategory category = productCategoryDaoDb.find(category_id);
            Supplier supplier = supplierDaoDb.find(supplier_id);

            Product product = new Product(name, price, currency, description, category, supplier);
            product.setId(id);
            return product;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = Queries.removeProduct();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getAllProducts();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Product> result = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int category_id = resultSet.getInt(2);
                int supplier_id = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String description = resultSet.getString(5);
                BigDecimal price = new BigDecimal(resultSet.getInt(6));
                String currency = resultSet.getString(7);

                ProductCategory category = productCategoryDaoDb.find(category_id);
                Supplier supplier = supplierDaoDb.find(supplier_id);

                Product product = new Product(name, price, currency, description, category, supplier);
                product.setId(id);
                result.add(product);
            }
            return result;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getProductsBySupplier();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> result = new ArrayList<>();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int category_id = resultSet.getInt(2);
                int supplier_id = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String description = resultSet.getString(5);
                BigDecimal price = new BigDecimal(resultSet.getInt(6));
                String currency = resultSet.getString(7);

                ProductCategory category = productCategoryDaoDb.find(category_id);
                Supplier currentSupplier = supplierDaoDb.find(supplier_id);

                Product product = new Product(name, price, currency, description, category, currentSupplier);
                product.setId(id);
                result.add(product);
            }
            return result;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getProductsByCategory();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> result = new ArrayList<>();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int category_id = resultSet.getInt(2);
                int supplier_id = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String description = resultSet.getString(5);
                BigDecimal price = new BigDecimal(resultSet.getInt(6));
                String currency = resultSet.getString(7);

                ProductCategory category = productCategoryDaoDb.find(category_id);
                Supplier supplier = supplierDaoDb.find(supplier_id);

                Product product = new Product(name, price, currency, description, category, supplier);
                product.setId(id);
                result.add(product);
            }
            return result;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
