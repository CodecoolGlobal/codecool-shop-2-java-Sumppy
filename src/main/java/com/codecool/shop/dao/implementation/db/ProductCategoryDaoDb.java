package com.codecool.shop.dao.implementation.db;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.queries.Queries;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductCategoryDaoDb implements ProductCategoryDao {
    private DataSource dataSource;

    public ProductCategoryDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.addCategory();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDepartment());
            statement.setString(4, category.getDescription());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getCategory();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            String name = resultSet.getString(2);
            String department = resultSet.getString(3);
            String description = resultSet.getString(4);

            ProductCategory category = new ProductCategory(name, department, description);

            return category;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = Queries.removeCategory();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getAllCategories();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<ProductCategory> result = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String department = resultSet.getString(3);
                String description = resultSet.getString(4);

                ProductCategory category = new ProductCategory(name, department, description);
                category.setId(id);
                result.add(category);
            }
            return result;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
