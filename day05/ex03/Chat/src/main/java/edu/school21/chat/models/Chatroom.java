package edu.school21.chat.models;

import java.util.ArrayList;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private ArrayList<Message> messages;

    public Chatroom(long id, String name, User owner, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + messages.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Chatroom chatroom = (Chatroom) obj;
        return id == chatroom.id
                && name.equals(chatroom.name)
                && owner.equals(chatroom.owner)
                && messages.equals(chatroom.messages);
    }

    @Override
    public String toString() {
        return "User [id: " + id + " / name: " + name + " / owner: " + owner + "]\n";
    }

    public String printRooms(ArrayList<Message> someList) {
        if (someList.size() != 0) {
            StringBuilder str = new StringBuilder();
            for (Message some : someList) {
                str.append(some.toString());
                str.append("\n");
            }
            return str.toString();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
