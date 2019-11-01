delete from message;

insert into message(id, text, tag, user_id) values
(1, 'first', 'tag', 2),
(2, 'second', 'tag', 2),
(3, 'third', 'tag', 2),
(4, 'fourth', 'tag', 2);

alter sequence hibernate_sequence restart with 10;