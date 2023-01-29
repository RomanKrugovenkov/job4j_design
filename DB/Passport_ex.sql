create table passport(
    id serial primary key,
    name varchar(48),
    number char(10),
    adress text
);

insert into passport
values
(1, 'Roman', 653206, 'SPb'),
(2, 'Ivan', 975316, 'Msc'),
(3, 'Petr', 943056, 'Ekt');

select * from passport;

update passport
set name = 'Roma'
where name = 'Roman';

select * from passport;

delete from passport
where name = 'Petr';

select * from passport;