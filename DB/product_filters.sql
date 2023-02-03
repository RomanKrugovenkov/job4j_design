select t.name, p.name
from type t
         join product p on t.id = p.type_id
where t.name = 'cheese';

select p.name, t.name
from type t
         join product p on t.id = p.type_id
where p.name like '%ice-cream%';

select p.name, t.name, p.expired_date
from product p
         join type t on t.id = p.type_id
where p.expired_date < current_date;

select p.name, t.name, p.price
from product p
         join type t on t.id = p.type_id
where p.price = (Select MAX(price) from product);

select t.name product_type, count(t.name) Count_Of_Type
from product p
         join type t on t.id = p.type_id
group by t.name;

select p.name
from product p
         join type t on t.id = p.type_id
where t.name = 'milk' OR t.name = 'cheese';

select t.name product_type, count(t.name) Count_Of_Type
from product p
         join type t on t.id = p.type_id
group by t.name
having count(t.name) < 10;

select p.name, t.name
from product p
         join type t on t.id = p.type_id;