package edu.school21.chat.models;

import java.util.List;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Chatroom> rooms;
    private List<Chatroom> socializeRooms;

    public User(long id, String login, String password, List<Chatroom> rooms, List<Chatroom> socializeRooms) {
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

    public String printRooms(List<Chatroom> someList) {
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

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getRooms() {
        return rooms;
    }

    public List<Chatroom> getSocializeRooms() {
        return socializeRooms;
    }
}
