create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Golden fish', 1000, '1990-05-04');
insert into fauna (name, avg_age, discovery_date) values ('Golden enimal', 15000, '1890-09-04');
insert into fauna (name, avg_age, discovery_date) values ('Golden bird', 100500, '990-01-02');
insert into fauna (name, avg_age) values ('Golden crab', 500);

select * from fauna where name like '%fish%';

select * from fauna where avg_age < 21000 and avg_age > 10000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';