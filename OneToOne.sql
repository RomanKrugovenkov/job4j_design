create table phone_num(
    id serial primary key,
    region int,
    number int
);

create table person(
    id serial primary key,
    name varchar(255),
    phone_id int references phone_num(id) unique
);

insert into phone_num(region, number) values
(+7, 9241272),
(+3, 6753512);

insert into person(name, phone_id) values
('Roman', 2),
('Ivan', 1);