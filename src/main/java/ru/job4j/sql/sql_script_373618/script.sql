create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Mic', 6000);
insert into devices (name, price) values ('Cam', 20000);
insert into devices (name, price) values ('Mouse', 2000);
insert into devices (name, price) values ('Keyboard', 3500);

insert into people (name) values ('Den');
insert into people (name) values ('Max');
insert into people (name) values ('Nicky');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (4, 1);

insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (1, 2);

insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 3);

select avg(price) from devices;

select dp.people_id, avg(d.price)
from devices_people as dp join devices as d
on dp.device_id = d.id
group by dp.people_id;

select dp.people_id, avg(d.price)
from devices_people as dp join devices as d
on dp.device_id = d.id
group by dp.people_id
having avg(d.price) > 5000;

select p.name, avg(d.price) from 
devices_people as dp, people as p, devices as d
where dp.device_id = d.id and dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;