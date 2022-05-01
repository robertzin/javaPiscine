package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product;");
        while (resultSet.next()) {
            productList.add(new Product(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            ));
        }

        statement.close();
        connection.close();
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE id = " + id + ";");
        if (!resultSet.next()) {
            throw new RuntimeException();
        }

        Product product = new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getInt(3));

        statement.close();
        connection.close();
        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product"
                + " SET name = ?, price = ? "
                + " WHERE id = ?;"
        );

        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?);");

        preparedStatement.setLong(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setInt(3, product.getPrice());

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM product WHERE id = ?;");

        preparedStatement.setLong(1, id);

        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }
}
