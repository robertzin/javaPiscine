package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static edu.school21.sockets.app.Main.serverList;

public class Server extends Thread {

    private String username;
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        usersService = context.getBean("usersService", UsersService.class);
        usersRepository = context.getBean("usersRepository", UsersRepository.class);
        firstSteps();
        start();
    }

    public void firstSteps() {
        out.println("Hello from Server");
        try {
            String cmd = in.readLine();
            if (cmd.equals("signUp"))
                signUp();
            else if (cmd.equals("signIn"))
                signIn();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void signUp() {
        try {
            out.println("Enter username");
            username = in.readLine();
            out.println("Enter password");
            String password = in.readLine();
            if (!usersService.signUp(usersRepository.getMaxId(), username, password).equals("")) {
                out.println("Successful!");
            } else {
                out.println("Failure!");
            }
        } catch (Exception ignored) {}
    }

    public void signIn() {
        try {
            out.println("Enter username");
            username = in.readLine();
            out.println("Enter password");
            String password = in.readLine();
            if (usersService.signIn(username, password)) {
                out.println("Successful!");
            } else {
                out.println("Failure!");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            this.closeConnection();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void closeConnection() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Server server : serverList) {
                    if (server.equals(this)) {
                        server.interrupt();
                        serverList.remove(this);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String message;
        try {
            message = in.readLine();
            out.println(message);
            try {
                while (true) {
                    message = in.readLine();
                    if (message != null){
                        if (message.equals("Exit")) {
                            socket.close();
                            break;
                        }
                    }
                    for (Server server : serverList) {
                        server.sendMessage(message);
                    }
                    if (message != null) {
                        if (!message.equals("")) {
                            usersService.saveMessage(message);
                        }
                    }
                }
            } catch (IOException ignored) {}
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
