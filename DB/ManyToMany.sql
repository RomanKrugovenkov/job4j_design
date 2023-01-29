 create table child(
     id serial primary key,
     name varchar(255)
 );
 
 create table sport(
     id serial primary key,
     sport_type varchar(255)
 );
 
 create table childs_sport(
     id serial primary key,
     child_id int references child(id),
     sport_id int references sport(id)
 );
 
insert into child(name) values 
('Ivan'),
('Roman'),
('Petr');

insert into sport(sport_type) values 
('football'),
('swimming'),
('running');

insert into childs_sport(child_id, sport_id) values
(1, 1),
(1, 2),
(2, 3),
(2, 1);