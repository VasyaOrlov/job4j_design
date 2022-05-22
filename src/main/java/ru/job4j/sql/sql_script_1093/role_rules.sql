create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);