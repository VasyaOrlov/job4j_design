create table users(
	id serial primary key,
	name varchar(10),
	role_id int references role(id)
);