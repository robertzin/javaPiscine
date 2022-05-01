DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

DROP TABLE IF EXISTS chat.user, chat.chatroom, chat.message;

CREATE TABLE IF NOT EXISTS chat.user (
    id SERIAL PRIMARY KEY,
    login VARCHAR (15) UNIQUE NOT NULL,
    password VARCHAR (15) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom (
    id SERIAL PRIMARY KEY,
    name VARCHAR (15) UNIQUE NOT NULL,
    owner INTEGER NOT NULL,
    FOREIGN KEY (owner) REFERENCES chat.user(id)
);

CREATE TABLE IF NOT EXISTS chat.message (
    id SERIAL PRIMARY KEY,
    author INTEGER NOT NULL,
    FOREIGN KEY (author) REFERENCES chat.user(id),
    chatroom INTEGER NOT NULL,
    FOREIGN KEY (chatroom) REFERENCES chat.chatroom(id),
    text TEXT,
    date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat.many_to_many (
    user_id SERIAL,
    chatroom_id SERIAL,
    PRIMARY KEY (user_id, chatroom_id),
    FOREIGN KEY(user_id) REFERENCES chat.user(id),
    FOREIGN KEY(chatroom_id) REFERENCES chat.chatroom(id)
);
