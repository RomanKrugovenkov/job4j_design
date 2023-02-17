--транзакция в консоле
begin transaction;
set transaction isolation level serializable;

--получаем сумму товаров, а потом меняем строку в процессе транзакции
select sum(count) from items;

update items set count = 55 where name = 'item_1';

commit;
--транзакция завершена успешно