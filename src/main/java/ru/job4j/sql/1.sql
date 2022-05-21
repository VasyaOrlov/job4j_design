create table hockeyEquipment(
	id serial primary key,
	name varchar(255),
	coint integer,
	work boolean,
	price double precision
);
insert into hockeyEquipment (name, coint, work, price) values ('Helmet', 20, true, 109.95);
select * from hockeyEquipment;
update hockeyEquipment set coint = 4;
select * from hockeyEquipment;
delete from hockeyEquipment;
select * from hockeyEquipment;