package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Optional;
import java.sql.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> retMessage = null;

        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM chat.message WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            User user = new User(1, "username", "qwerty", null, null);
            Chatroom chatroom = new Chatroom(1, "chatroom", null, null);
            retMessage = Optional.of(new Message(resultSet.getInt(1), user, chatroom, resultSet.getString("text"), LocalDateTime.now()));
        } catch (SQLException throwables) {
            System.out.println("Error: " + throwables.getMessage());
        }
        return retMessage;
    }
}
