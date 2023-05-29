-- вывод имен сотрудников не состоящих в company_id = 5
select p.name
from person p
where NOT p.company_id = 5;

-- вывод имен сотрудников и соответствующих имен компаний
select p.name, c.name
from person p
         join company c on c.id = p.company_id;

-- выбрать название компании с макс количеством человек + количество человек в этой компании
select c.name, count(p.company_id) as count_person
from company c
         join person p on c.id = p.company_id
group by c.name
having count(p.company_id) =
       (select count(company_id) as c_c
        from person p
        group by company_id
        order by c_c desc
        limit 1);