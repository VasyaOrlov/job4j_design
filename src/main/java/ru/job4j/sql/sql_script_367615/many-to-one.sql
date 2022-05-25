create table master (
	id serial primary key,
	name varchar (255)
);

create table pet (
	id serial primary key,
	nick varchar (255),
	master_id int references master (id)
);

insert into master(name) values ('Kolya');
insert into pet (nick, master_id) values ('Barsik', 1);