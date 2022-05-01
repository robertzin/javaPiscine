package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        dataSource.setUsername("postgres");
        dataSource.setPassword("111");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);
        User creator = new User(1L, "user", "user", new ArrayList(), new ArrayList());
        Chatroom room = new Chatroom(2L, "room", creator, new ArrayList());
        Message message = new Message(null, creator, room, "Hello!", LocalDateTime.now());

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}
