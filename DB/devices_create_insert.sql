create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price)
values ('watch', 4000),
       ('smartphone', 15000),
       ('laptop', 80000);

insert into people(name)
values ('Roman'),
       ('Ivan'),
       ('Petr');

insert into devices_people(people_id, device_id)
values (1, 1),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 3);