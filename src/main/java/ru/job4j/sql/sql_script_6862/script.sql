create table departaments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255),
	departaments_id int references departaments(id)
);

insert into departaments (name) values ('Main');
insert into departaments (name) values ('Secondary');
insert into departaments (name) values ('Security');

insert into employees (name, departaments_id) values ('Den', 1);
insert into employees (name, departaments_id) values ('Max', 3);
insert into employees (name) values ('Maxim');

select * from
employees as e left join departaments as d
on e.departaments_id = d.id;

select * from
departaments as d right join employees as e
on e.departaments_id = d.id;

select * from
departaments as d full join employees as e
on e.departaments_id = d.id;

select * from
employees as e cross join departaments as d;

select * from
departaments as d left join employees as e
on e.departaments_id = d.id
where e.departaments_id is null;


select e.name , e.departaments_id as dep, d.name from
employees as e left join departaments as d
on e.departaments_id = d.id;
select e.name, e.departaments_id, d.name from
departaments as d right join employees as e
on e.departaments_id = d.id;

create table teens (
	id serial primary key,
	name varchar(255) unique,
	gender varchar(255)
)

insert into teens (name, gender) values ('Den', 'man');
insert into teens (name, gender) values ('Jon', 'man');
insert into teens (name, gender) values ('Maik', 'man');

insert into teens (name, gender) values ('Kate', 'woman');
insert into teens (name, gender) values ('Sara', 'woman');

select n1.name, n2.name, (n1.name || '+' ||  n2.name) as Family
from teens n1 cross join teens n2
where n1.gender != n2.gender;