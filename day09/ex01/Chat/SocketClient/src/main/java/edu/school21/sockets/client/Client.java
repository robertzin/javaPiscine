package edu.school21.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    private Socket socket;
    LocalDateTime time;
    private BufferedReader reader;
    private BufferedReader in;
    private PrintWriter out;
    String username;
    String password;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = reader = new BufferedReader(new InputStreamReader(System.in));;
        this.in = in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = out = new PrintWriter(socket.getOutputStream(), true);
        firstSteps();
        new ReadMsg().start();
        new WriteMsg().start();
    }

    public void firstSteps() throws IOException {

        String serverWord = in.readLine();
        if (serverWord == null)
            System.exit(1);
        System.out.println(serverWord);
        System.out.print("> ");
        out.println(reader.readLine());

        serverWord = in.readLine();
        if (serverWord == null)
            System.exit(1);
        System.out.println(serverWord);
        System.out.print("> ");
        username = reader.readLine();
        out.println(username);

        serverWord = in.readLine();
        if (serverWord == null)
            System.exit(1);
        System.out.println(serverWord);
        System.out.print("> ");
        password = reader.readLine();
        out.println(password);

        serverWord = in.readLine();
        System.out.println(serverWord);
        if (serverWord.equals("Successful!"))
            System.out.println("Start messaging");
        else if (serverWord.equals("Failure!")) {
            System.err.println("Some error occured!");
            this.closeConnection();
            System.exit(1);
        }

//        System.out.println(in.readLine());
//
//        out.println(reader.readLine());
//        String serverWord = in.readLine();
//        System.out.println(serverWord);
//        username = reader.readLine();
//        out.println(username);
//
//        out.println(reader.readLine());
//        serverWord = in.readLine();
//        System.out.println(serverWord);
//        password = reader.readLine();
//        out.println(password);
//
    }

    public void closeConnection() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                reader.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("Exit")) {
                        Client.this.closeConnection();
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
                Client.this.closeConnection();
            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    time = LocalDateTime.now();
                    userWord = reader.readLine();
                    if (userWord.equals("Exit")) {
                        out.println("Exit");
                        Client.this.closeConnection();
                        System.out.println("You have left the chat.");
                        break;
                    } else
                        out.println("(" + time + ") " + username + ": " + userWord);
                } catch (IOException e) {
                    Client.this.closeConnection();
                }
            }
        }
    }
}