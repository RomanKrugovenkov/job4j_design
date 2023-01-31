create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('big fish', 900, '01.06.1960'),
       ('leopard', 5000, '10.02.1850'),
       ('elephant', 15000, '11.11.1855'),
       ('eagle', 3500, null);