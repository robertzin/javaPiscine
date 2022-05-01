package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.operateClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
