--Создать таблицы cars:
create table car_bodies
(
    id   serial primary key,
    name varchar(64)
);

create table car_engines
(
    id   serial primary key,
    name varchar(64)
);

create table car_transmissions
(
    id   serial primary key,
    name varchar(64)
);

create table cars
(
    id              serial primary key,
    name            varchar(64),
    body_id         int REFERENCES car_bodies (id),
    engine_id       int REFERENCES car_engines (id),
    transmission_id int REFERENCES car_transmissions (id)
);

insert into car_bodies(name)
values ('sedan'),
       ('wagon'),
       ('hatchback'),
       ('jeep');

insert into car_engines(name)
values ('1.6t'),
       ('2.0'),
       ('3.5'),
       ('4.0');

insert into car_transmissions(name)
values ('mechanical'),
       ('automatic'),
       ('robotic'),
       ('variator');

insert into cars(name, body_id, engine_id, transmission_id)
values ('mini_hatch', 3, 1, 2),
       ('BMW_3', 1, 2, 1),
       ('audi_6', 2, 3, 3),
       ('mazda_3', 1, null, 1),
       ('kia_ceed', 3, 2, null);