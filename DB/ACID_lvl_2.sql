--транзакция в консоле
begin transaction;
set transaction isolation level repeatable read;

select *
from items;

--меняем данные в консоле в процессе транзакции + commit
insert into items (name, count, price)
VALUES ('item_5', 11, 64);
delete
from items
where price = 100;
update items
set price = 75
where name = 'item_1';

select *
from items;

commit;

--меняем данные в консоле в процессе транзакции + rollback
begin transaction;
set transaction isolation level repeatable read;

update items
set price = 75
where name = 'item_2';

rollback;
