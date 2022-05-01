package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;

    public Message(long id, User author, Chatroom room, String text, LocalDateTime date) {
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
        LocalDateTime now = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);

        return "Message : {\nid=" + id
                + ",\nauthor={id= " + author.getId() + ",login=" +"\"" + author.getLogin()
                + ",password=" + author.getPassword() + ",CreatedRooms=" + author.getRooms() + ",rooms="
                + author.getSocializeRooms() + "},\n"
                + "room={id=" + room.getId() + ",name=" + room.getName() + ",creator=" + room.getOwner()
                + ",messages=" + room.getMessages() + "},\n"
                + "text=\"" + text + "\"\ndateTime=" + formatDateTime + "\n}";
    }
}
