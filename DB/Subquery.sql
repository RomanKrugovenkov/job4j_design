CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers
VALUES (1, 'Ivan', 'Ivanov', 30, 'Canada'),
       (2, 'Oleg', 'Olegov', 35, 'USA'),
       (3, 'Egor', 'Egorov', 30, 'Mexica'),
       (4, 'Roman', 'Romanov', 40, 'Brazil');

--вывод списка клиентов с минимальным возрастом
select * from customers
where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 3, 1),
       (2, 1, 3);

--вывод списка клиентов без заказов
select * from customers
where id not in (select customer_id from orders);