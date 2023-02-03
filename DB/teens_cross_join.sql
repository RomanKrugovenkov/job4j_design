--Создать таблицу teens с атрибутами name, gender.
--Используя cross join составить все возможные разнополые пары
create table teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);

insert into teens(name, gender)
values ('roman', 'male'),
       ('ivan', 'male'),
       ('egor', 'male'),
       ('masha', 'female'),
       ('sveta', 'female'),
       ('marina', 'female');

select tm.name, tf.name
from teens tm
         cross join teens tf
where tm.gender != tf.gender;