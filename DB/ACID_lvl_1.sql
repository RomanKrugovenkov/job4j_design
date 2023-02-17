create table items
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into items (name, producer, count, price)
VALUES ('item_1', 'producer_1', 3, 50),
       ('item_2', 'producer_2', 5, 40),
       ('item_3', 'producer_3', 7, 60),
       ('item_4', 'producer_4', 10, 100);

--Чтение подтвержденных данных (read committed)
--транзакция в консоле
begin transaction;

select *
from items;

--меняем данные в консоле в процессе транзакции
insert into items (name, count, price)
VALUES ('item_5', 11, 64);
delete
from items
where price = 100;
update items
set price = 75
where name = 'item_1';

--вывод в текущей транзакции и параллельной будут отличаться
--феномен грязного чтения во второй транзакции отсутствует.
select *
from items;

--фиксируем изменения. теперь они видны в параллельной транзакции
commit;

select *
from items;