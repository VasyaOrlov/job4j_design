create table type (
	id serial primary key,
	name varchar(255)
);

create table product (
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Мороженое');

insert into product (name, type_id, expired_date, price)
values ('Сыр плавленный', 1, '2022-06-15', 100);
insert into product (name, type_id, expired_date, price)
values ('Сыр моцарелла', 1, '2022-05-30', 200);
insert into product (name, type_id, expired_date, price)
values ('Молоко деревенское', 2, '2022-05-30', 80);
insert into product (name, type_id, expired_date, price)
values ('Молоко вкусное', 2, '2022-05-22', 75);
insert into product (name, type_id, expired_date, price)
values ('Мороженное клубничное', 3, '2022-03-30', 45);
insert into product (name, type_id, expired_date, price)
values ('Мороженное шоколадное', 3, '2022-05-30', 45);

select * from product where product.type_id = 1;

select pr.name, pr.type_id, pr.expired_date, pr.price from
product as pr join type
on pr.type_id = type.id
where type.name = 'Сыр';

select * from product 
where product.type_id = (select id from type where name = 'Сыр');

select * from product
where product.name like '%Мороженное%'

select * from product where product.expired_date < current_date;

select * from product 
where product.price = (select max(product.price) from product);

select type.name, count(product.type_id) 
from product join type
on product.type_id = type.id
group by type.name;

select * from product where product.type_id = 1 or product.type_id = 2;

select pr.name, pr.type_id, pr.expired_date, pr.price from
product as pr join type
on pr.type_id = type.id
where type.name = 'Сыр' or type.name = 'Молоко';

select * from product 
where product.type_id = (select id from type where name = 'Сыр')
or product.type_id = (select id from type where name = 'Молоко');

select type.name 
from product join type
on product.type_id = type.id
group by type.name
having count(product.type_id) < 10;

select product.name as Product, type.name as Type
from product join type
on product.type_id = type.id;