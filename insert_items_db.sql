insert into role(name) values
('admin'),
('guest');

insert into users(name, role_id) values
('Petr', 1),
('Roman', 2);

insert into rules(name) values
('admin access'),
('full access'),
('limited access');

insert into role_rules(role_id, rules_id) values
(1, 1),
(1, 2),
(2, 3);

insert into category(name) values
('critical'),
('standard');

insert into state(name) values
('active'),
('inactive');

insert into item(name, users_id, category_id, state_id) values
('001', 1, 1, 1),
('002', 2, 2, 2);

insert into comments(name, item_id) values
('insufficient data', 1),
('execute tomorrow', 2);

insert into attachs(name, item_id) values
('log001.txt', 1),
('log002.txt', 1);