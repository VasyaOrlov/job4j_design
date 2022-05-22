create table comments(
	id serial primary key,
	name varchar(10),
	item_id int references item(id)
);