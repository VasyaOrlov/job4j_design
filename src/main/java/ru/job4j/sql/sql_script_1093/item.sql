create table item(
	id serial primary key,
	name varchar(10),
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);