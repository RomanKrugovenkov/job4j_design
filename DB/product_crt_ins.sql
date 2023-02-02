create table type
(
    id   serial primary key,
    name varchar(64) not null
);

create table product
(
    id           serial primary key,
    type_id      int references type (id),
    name         varchar(64) not null,
    expired_date date,
    price        float
);

insert into type(name)
values ('milk'),
       ('cheese'),
       ('meat and fish'),
       ('sweets');

insert into product(type_id, name, expired_date, price)
values (1, 'milk', '2023.02.10', 2.5),
       (2, 'dutch cheese', '2023.04.15', 9.0),
       (2, 'cream cheese', '2023.03.15', 9.9),
       (2, 'mozzarella cheese', '2023.01.15', 7.9),
       (3, 'pork', '2023.01.05', 7.7),
       (3, 'beef', '2023.02.07', 12.5),
       (4, 'ice-cream', '2023.01.22', 1.9),
       (4, 'cookies', '2023.03.01', 1.2);