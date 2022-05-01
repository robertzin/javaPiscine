package edu.school21.chat.models;

import java.util.ArrayList;

public class User {
    private Long id;
    private String login;
    private String password;
    private ArrayList<Chatroom> rooms;
    private ArrayList<Chatroom> socializeRooms;

    public User(Long id, String login, String password, ArrayList<Chatroom> rooms, ArrayList<Chatroom> socializeRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rooms = rooms;
        this.socializeRooms = socializeRooms;
    }


    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + rooms.hashCode();
        result = 31 * result + socializeRooms.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id
                && login.equals(user.login)
                && password.equals(user.password)
                && rooms.equals(user.rooms)
                && socializeRooms.equals(user.socializeRooms);
    }

    @Override
    public String toString() {
        return "User [id: " + id + " / login: " + login + " / password: " + password + "]\n";
    }

    public String printRooms(ArrayList<Chatroom> someList) {
        if (someList.size() != 0) {
            StringBuilder str = new StringBuilder();
            for (Chatroom some : someList) {
                str.append(some);
                str.append("\n");
            }
            return str.toString();
        }
    return null;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Chatroom> getRooms() {
        return rooms;
    }

    public ArrayList<Chatroom> getSocializeRooms() {
        return socializeRooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRooms(ArrayList<Chatroom> rooms) {
        this.rooms = rooms;
    }

    public void setSocializeRooms(ArrayList<Chatroom> socializeRooms) {
        this.socializeRooms = socializeRooms;
    }
}
