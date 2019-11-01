delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$iXqFMQPEzd9Oo9GjFe8AU.pwIStIwmKLGE4Q7xanv0teGWkue6.Jy', 'admin'),
(2, true, '$2a$08$iXqFMQPEzd9Oo9GjFe8AU.pwIStIwmKLGE4Q7xanv0teGWkue6.Jy', 'user');

insert into user_role(user_id, roles) values
(1, 'USER'),
(1, 'ADMIN'),
(2, 'USER');