CREATE TABLE company
(
    id   SERIAL CONSTRAINT person_Id PRIMARY KEY,
    name character varying
);

insert into company(name)
values ('BMW'),
       ('AUDI'),
       ('Mazda'),
       ('MINI'),
       ('KIA');

CREATE TABLE person
(
    id         SERIAL CONSTRAINT person_Id PRIMARY KEY,
    name       character varying,
    company_id integer references company (id)
);

insert into person(name, company_id)
values ('Oliver', 1),
       ('Jack', 1),
       ('Alice', 2),
       ('Helen', 2),
       ('Michael', 3),
       ('Peter', 3),
       ('Alex', 3),
       ('Sophia', 4),
       ('Julia', 4),
       ('Ann', 4),
       ('Boris', 5),
       ('Daniel', 5),
       ('Dora', 5),
       ('Irene', 5);