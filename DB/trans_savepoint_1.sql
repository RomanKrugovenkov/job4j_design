begin transaction;

insert into teens (name, gender)
VALUES ('kolia', 'male');

--добавляем точку сохранения
savepoint first_savepoint;

delete
from teens
where gender = 'female';

update teens
set gender = null
where name = 'kolia';

select * from teens;

--делаем откат и проверяем данные
rollback to first_savepoint;

select * from teens;

commit;