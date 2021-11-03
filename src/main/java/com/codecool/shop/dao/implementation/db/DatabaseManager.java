package com.codecool.shop.dao.implementation.db;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    private OrderDaoDb orderDaoDb;
    private ProductCategoryDaoDb productCategoryDaoDb;
    private ProductDaoDb productDaoDb;
    private SupplierDaoDb supplierDaoDb;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        orderDaoDb = new OrderDaoDb(dataSource);
        productCategoryDaoDb = new ProductCategoryDaoDb();
        productDaoDb = new ProductDaoDb();
        supplierDaoDb = new SupplierDaoDb();
    }

    private DataSource connect() throws SQLException{
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DATA_BASE");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
