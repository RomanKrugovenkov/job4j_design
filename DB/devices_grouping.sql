select avg(price)
from devices;

select p.name, avg(d.price)
from people p
         join devices_people dp on p.id = dp.people_id
         join devices d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price)
from people p
         join devices_people dp on p.id = dp.people_id
         join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 10000;