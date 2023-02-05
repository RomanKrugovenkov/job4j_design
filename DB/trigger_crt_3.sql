create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

--Триггер должен срабатывать после вставки данных и насчитывать налог на товар
--statement уровень
create or replace function tax_20()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.2
    where id = (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger trg_tax_20_after
    after insert
    on products
    referencing new table as inserted
    for each statement
execute procedure tax_20();

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 8, 115);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 3, 50);

select *
from products;

alter table products
    disable trigger trg_tax_20_after;

--Триггер должен срабатывать до вставки данных и насчитывать налог на товар
--row уровень
create or replace function tax_20_row()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.2
    where id = new.id;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger trg_tax_20_before
    before insert
    on products
    for each row
execute procedure tax_20_row();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 22, 100);
insert into products (name, producer, count, price)
VALUES ('product_4', 'producer_4', 33, 10);

select *
from products;

--написать триггер на row уровне, который при вставке продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function log_insert()
    returns trigger as
$$
BEGIN
    insert into history_of_price(name, price, date)
    values (new.name, new.price, current_timestamp);
    return null;
END;
$$
    LANGUAGE 'plpgsql';

create trigger trg_log_insert
    after insert
    on products
    for each row
execute procedure log_insert();

insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 12, 1000);
insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 24, 50);

select *
from history_of_price;