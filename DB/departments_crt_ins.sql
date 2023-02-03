create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id       serial primary key,
    name     varchar(255),
    dep_id int references departments (id)
);

insert into departments(name)
values ('transport'),
       ('supply'),
       ('finance'),
       ('technology');

insert into employees(name, dep_id)
values ('roman', 1),
       ('ivan', 1),
       ('petr', 2),
       ('marina', null),
       ('kirill', 3),
       ('maxim', null);