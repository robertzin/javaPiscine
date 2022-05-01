package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket("localhost", 9000));
    }
}
