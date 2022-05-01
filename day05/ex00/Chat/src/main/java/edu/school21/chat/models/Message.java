package edu.school21.chat.models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;

    public Message(int id, User author, Chatroom room, String text, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + author.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return id == message.id
                && author.equals(message.author)
                && room.equals(message.room)
                && text.equals(message.text)
                && date.equals(message.date);
    }

    @Override
    public String toString() {
        return "User [id: " + id + " / author: " + author + " / room: " + room
                + " / text: " + text + " / room: " + date + "]\n";
    }
}
