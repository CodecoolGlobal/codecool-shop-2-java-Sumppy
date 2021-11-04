package com.codecool.shop.dao.implementation.db;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.queries.Queries;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDb implements SupplierDao {
    private DataSource dataSource;

    public SupplierDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.addSupplier();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getSupplier();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                return null;
            }

            String name = resultSet.getString(2);
            String description = resultSet.getString(3);

            Supplier supplier = new Supplier(name, description);

            return supplier;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = Queries.removeSupplier();
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection connection = dataSource.getConnection()){
            String sql = Queries.getAllSuppliers();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Supplier> result = new ArrayList<>();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);

                Supplier supplier = new Supplier(name, description);
                supplier.setId(id);
                result.add(supplier);
            }
            return result;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }    }
}
