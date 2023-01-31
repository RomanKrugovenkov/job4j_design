CREATE TABLE publisher
(
    id     serial primary key,
    appellation   varchar(255),
    adress text
);

CREATE TABLE book
(
    id           serial primary key,
    name         varchar(255),
    publisher_id int references publisher (id)
);

INSERT INTO publisher(appellation, adress)
VALUES ('union printer', 'Msc'),
       ('first publisher', 'SPb'),
       ('alphabet', 'Ekt');


INSERT INTO book(name, publisher_id)
VALUES ('war and peace', 1),
       ('the strange case', 3),
       ('jane eyre', 2);

SELECT b.name, p.appellation, p.adress
FROM book AS b JOIN publisher AS p on b.publisher_id = p.id;

SELECT b.name Книга, p.appellation Издатель, p.adress Адрес
FROM book b JOIN publisher p on b.publisher_id = p.id;

SELECT b.name "Название книги", p.appellation "Имя издателя", p.adress "Адрес"
FROM book b JOIN publisher p on b.publisher_id = p.id;