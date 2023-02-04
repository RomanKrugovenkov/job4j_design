--Вывести список всех машин и все привязанные к ним детали
select c.id, c.name, cb.name, ce.name, ct.name
from cars c
         left join car_bodies cb on c.body_id = cb.id
         left join car_engines ce on c.engine_id = ce.id
         left join car_transmissions ct on c.transmission_id = ct.id;

--Вывести кузовы, которые не используются НИ в одной машине
select cb.name "Car's Body", c.name "Car's Name"
from car_bodies cb
         left join cars c on cb.id = c.body_id
where c IS NULL ;

--Вывести двигатели, которые не используются НИ в одной машине
select ce.name "Car's Engine", c.name "Car's Name"
from car_engines ce
         left join cars c on ce.id = c.body_id
where c IS NULL;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct.name "Car's Engine", c.name "Car's Name"
from car_transmissions ct
         left join cars c on ct.id = c.body_id
where c IS NULL;