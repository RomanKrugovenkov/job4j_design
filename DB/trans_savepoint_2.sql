begin transaction;

select * from teens;

--удаляем данные из таблицы и саму таблицу
delete from teens;

drop table teens;

--делаем откат и проверяем данные
rollback;

select * from teens;

commit;