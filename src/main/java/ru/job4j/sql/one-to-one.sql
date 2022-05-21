create table people (
	id serial primary key,
	name varchar (255)
);

create table imprint (
	id serial primary key,
	size bigint
);

create table base (
	id serial primary key,
	people_id int references people (id) unique,
	imprint_id int references imprint (id) unique
);

insert into people (name) values ('Kolya');
insert into imprint (size) values (256756756);
insert into base (people_id, imprint_id) values (1, 1);