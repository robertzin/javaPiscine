package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;

public class Client {

    public void operateClient() throws IOException {

        try (Socket clientSocket = new Socket("localhost", 9000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

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
            out.println(reader.readLine());

            serverWord = in.readLine();
            if (serverWord == null)
                System.exit(1);
            System.out.println(serverWord);
            System.out.print("> ");
            out.println(reader.readLine());

            System.out.println(in.readLine());

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}