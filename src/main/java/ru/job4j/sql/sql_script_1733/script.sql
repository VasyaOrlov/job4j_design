create table body (
	id serial primary key,
	name varchar(255)
);

create table engine (
	id serial primary key,
	name varchar(255)
);

create table transmission (
	id serial primary key,
	name varchar(255)
);

create table car (
	id serial primary key,
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body (name) values ('sedan');
insert into body (name) values ('minivan');
insert into body (name) values ('coupe');

insert into engine (name) values ('v6');
insert into engine (name) values ('v8');
insert into engine (name) values ('v12');
insert into engine (name) values ('nothink');

insert into transmission (name) values ('auto');
insert into transmission (name) values ('manual');
insert into transmission (name) values ('nothink');

insert into car (body_id, engine_id, transmission_id) values (1, 2, 1);
insert into car (body_id, engine_id, transmission_id) values (3, 3, 1);
insert into car (body_id, engine_id, transmission_id) values (1, 1, 2);

select * from
car left join body
on car.body_id = body.id
left join engine
on car.engine_id = engine.id
left join transmission
on car.transmission_id = transmission.id;

select * from
body left join (select body_id from car) as n1
on body.id = n1.body_id
where body_id is null;

select * from
engine left join (select engine_id from car) as n1
on engine.id = n1.engine_id
where engine_id is null;

select * from
transmission left join (select transmission_id from car) as n1
on transmission.id = n1.transmission_id
where transmission_id is null;

select body.id, body.name from
body left join car
on body.id = car.body_id
where body_id is null;

select engine.id, engine.name from
engine left join car
on engine.id = car.engine_id
where engine_id is null;

select transmission.id, transmission.name from
transmission left join car
on transmission.id = car.transmission_id
where transmission_id is null;