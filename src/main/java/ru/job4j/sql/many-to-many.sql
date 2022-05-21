create table student (
	id serial primary key,
	name varchar (255)
);

create table subject (
	id serial primary key,
	name varchar(255)
);

create table student_base (
	id serial primary key,
	student_id int references student (id),
	subject_id int references subject (id) 
);

insert into student (name) values ('Kolya');
insert into subject (name) values ('Math');
insert into student_base (student_id, subject_id) values (1, 1);