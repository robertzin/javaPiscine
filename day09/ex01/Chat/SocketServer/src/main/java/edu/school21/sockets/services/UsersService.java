package edu.school21.sockets.services;

public interface UsersService {
    String signUp(Long id, String username, String pwd);
    boolean signIn(String username, String pwd);
    void saveMessage(String message);
}
