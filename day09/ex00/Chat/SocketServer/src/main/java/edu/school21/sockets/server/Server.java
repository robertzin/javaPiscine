package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    private UsersService usersService;
    private UsersRepository usersRepository;

    public Server(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    public void operateServer(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar target/socket-server.jar --port=[port]");
            System.exit(1);
        }
        PrintWriter out;
        BufferedReader in;

        int port = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));

        try (ServerSocket server = new ServerSocket(port)) {
            try (Socket clientSocket = server.accept()) {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println("Hello from Server");
                String cmd = in.readLine();

                if (cmd.equals("signUp")) {
                    out.println("Enter username");
                    String username = in.readLine();
                    if (username.equals(usersRepository.findByUsername(username).get().getUsername())) {
                        out.println("Error: username is duplicated!");
                        server.close();
                        clientSocket.close();
                        System.exit(1);
                    }
                    out.println("Enter password");
                    String password = in.readLine();
                    usersService.signUp(usersRepository.getMaxId(), username, password);
                    out.println("Successful!");

                } else
                    out.println("Unknown command");
            }
        }
    }
}

