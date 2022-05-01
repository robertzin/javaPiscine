package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean("usersService", UsersService.class);
        UsersRepository usersRepository = context.getBean("usersRepository", UsersRepository.class);
        Server server = new Server(usersService, usersRepository);
        try {
            server.operateServer(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
