create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('big fish', 900, '1960.06.01'),
       ('leopard', 5000, '1850.02.10'),
       ('elephant', 15000, '1855.11.11'),
       ('eagle', 3500, null);