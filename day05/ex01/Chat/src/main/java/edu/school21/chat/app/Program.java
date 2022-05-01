package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        dataSource.setUsername("postgres");
        dataSource.setPassword("111");

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an ID");
        System.out.println(repository.findById(scanner.nextLong()).get());;
    }
}
