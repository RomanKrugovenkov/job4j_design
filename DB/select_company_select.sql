-- вывод имен сотрудников не состоящих в company_id = 5
select p.name
from person p
where NOT p.company_id = 5;

-- вывод имен сотрудников и соответствующих имен компаний
select p.name, c.name
from person p
         join company c on c.id = p.company_id;