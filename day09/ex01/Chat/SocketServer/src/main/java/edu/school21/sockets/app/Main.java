package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Server> serverList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar target/socket-server.jar --port=[port]");
            System.exit(1);
        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean("usersService", UsersService.class);
        UsersRepository usersRepository = context.getBean("usersRepository", UsersRepository.class);
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1)))) {
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    serverList.add(new Server(socket));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    socket.close();
                }
            }
        }
    }
}
