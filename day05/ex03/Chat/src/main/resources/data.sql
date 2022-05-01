INSERT INTO chat.user (login, password) VALUES
('user', 'password'),
('root', 'qwerty'),
('guest', 'pswrd'),
('unknown', '12345'),
('player', 'lolkek');

INSERT INTO chat.chatroom (name, owner) VALUES
('trivial_chat', (SELECT id FROM chat.user WHERE login = 'user')),
('chat_roulette', (SELECT id FROM chat.user WHERE login = 'root')),
('ICQ', (SELECT id FROM chat.user WHERE login = 'guest')),
('Telegram', (SELECT id FROM chat.user WHERE login = 'unknown')),
('Whatsapp', (SELECT id FROM chat.user WHERE login = 'player'));

INSERT INTO chat.message (author, chatroom, text, date) VALUES
((SELECT id FROM chat.user WHERE login = 'user'),
 (SELECT id FROM chat.chatroom WHERE name = 'trivial_chat'),
 'Hello trivial_chat!', (SELECT CURRENT_TIMESTAMP)),

((SELECT id FROM chat.user WHERE login = 'root'),
 (SELECT id FROM chat.chatroom WHERE name = 'chat_roulette'),
 'Hello chat_roulette!', (SELECT CURRENT_TIMESTAMP)),

((SELECT id FROM chat.user WHERE login = 'guest'),
 (SELECT id FROM chat.chatroom WHERE name = 'ICQ'),
 'Hello ICQ!', (SELECT CURRENT_TIMESTAMP)),

((SELECT id FROM chat.user WHERE login = 'unknown'),
 (SELECT id FROM chat.chatroom WHERE name = 'Telegram'),
 'Hello Telegram!', (SELECT CURRENT_TIMESTAMP)),

((SELECT id FROM chat.user WHERE login = 'player'),
 (SELECT id FROM chat.chatroom WHERE name = 'Whatsapp'),
 'Hello Whatsapp!', (SELECT CURRENT_TIMESTAMP));

INSERT INTO chat.many_to_many (user_id, chatroom_id) VALUES
((SELECT id FROM chat.user WHERE login = 'user'),
 (SELECT id FROM chat.chatroom WHERE name = 'trivial_chat')),
((SELECT id FROM chat.user WHERE login = 'root'),
 (SELECT id FROM chat.chatroom WHERE name = 'chat_roulette')),
((SELECT id FROM chat.user WHERE login = 'guest'),
 (SELECT id FROM chat.chatroom WHERE name = 'ICQ')),
((SELECT id FROM chat.user WHERE login = 'unknown'),
 (SELECT id FROM chat.chatroom WHERE name = 'Telegram')),
((SELECT id FROM chat.user WHERE login = 'player'),
 (SELECT id FROM chat.chatroom WHERE name = 'Whatsapp'));



