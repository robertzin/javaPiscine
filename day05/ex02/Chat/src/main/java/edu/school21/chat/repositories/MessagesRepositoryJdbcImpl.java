package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

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

            User user = new User(1L, "username", "qwerty", null, null);
            Chatroom chatroom = new Chatroom(1, "chatroom", null, null);
            retMessage = Optional.of(new Message(resultSet.getLong("id"), user, chatroom, resultSet.getString("text"), LocalDateTime.now()));
        } catch (SQLException throwables) {
            System.out.println("Error: " + throwables.getMessage());
        }
        return retMessage;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException {
        if (message.getAuthor() == null || message.getRoom() == null)
            throw new NotSavedSubEntityException();

        String query = "INSERT INTO chat.message (author, chatroom, text, date) VALUES ("
                + message.getAuthor().getId() + ", "
                + message.getRoom().getId() + ", "
                + "'" + message.getText() + "', "
                + "'" + message.getDate() + "');";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            message.setId(resultSet.getLong("id"));
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new NotSavedSubEntityException();
        }
        return true;
    }
}
