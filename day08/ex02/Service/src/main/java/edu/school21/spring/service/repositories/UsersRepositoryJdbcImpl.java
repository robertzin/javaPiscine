package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("jdbcRepository")
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("jdbcDataSource")DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"user\" WHERE id = " + id + ";");
            if (!resultSet.next()) {
                throw new RuntimeException();
            }
            return new User(
                    resultSet.getLong(1),
                    resultSet.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            List<User> userList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"user\";");
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                ));
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User entity, String password) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO public.\"user\" VALUES (?, ?, ?)")) {
            if (entity.getId() != null)
                preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, password);

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE public.\"user\"" + " SET email = ?" + " WHERE id = ?;")) {

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM public.\"user\" WHERE id = ?;")) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"user\" WHERE email = " + email + ";");
            if (!resultSet.next()) {
                throw new RuntimeException();
            }

            User user = new User(resultSet.getLong(1), resultSet.getString(2));
            return Optional.of(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
