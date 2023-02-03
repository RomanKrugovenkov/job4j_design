--Выполнить запросы с left, right, full, cross соединениями
select *
from departments d
         left join employees e on d.id = e.dep_id;

select *
from departments d
         right join employees e on d.id = e.dep_id;

select *
from departments d
         full join employees e on d.id = e.dep_id;

select *
from departments d
         cross join employees;

--Используя left join найти департаменты, у которых нет работников
select *
from departments d
         full join employees e on d.id = e.dep_id
where e IS NULL;

--Используя left и right join написать запросы с одинаковым результатом
select *
from employees e
         left join departments d on e.dep_id = d.id;

select e.id, e.name, e.dep_id, d.id, d.name
from departments d
         right join employees e on d.id = e.dep_id;