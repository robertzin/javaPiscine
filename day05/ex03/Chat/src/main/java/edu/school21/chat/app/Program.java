package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        dataSource.setUsername("postgres");
        dataSource.setPassword("111");

        MessagesRepository messagesRepository = new
                MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(1L);
        if (messageOptional.isPresent()) {
            try {
                messagesRepository.update(messageOptional.get());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Can not find message by ID");
        }
    }
}
