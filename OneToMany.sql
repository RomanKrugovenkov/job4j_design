create table publisher(
    id serial primary key,
    name varchar(255),
	adress text
	);

create table book(
    id serial primary key,
    name varchar(255),
	publisher_id int references publisher(id)
);

insert into publisher(name, adress) values ('union printer', 'Msc');
insert into book(name, publisher_id) VALUES ('war and peace', 1);

select * from publisher;

select * from book where id in (select publisher_id from book);