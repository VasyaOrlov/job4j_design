create table team (
	id serial primary key,
	name varchar(15)
);

create table players (
	id serial primary key,
	name varchar(20),
	team_id int references team(id)
);

insert into team (name) values ('Dinamo');
insert into team (name) values ('CSKA');

insert into players (name, team_id) values ('Dima', 1);
insert into players (name, team_id) values ('Kolia', 1);
insert into players (name, team_id) values ('Petia', 1);

insert into players (name, team_id) values ('Dima', 2);
insert into players (name, team_id) values ('Den', 2);
insert into players (name, team_id) values ('Roma', 2);

select team.name, players.name
from team  
join players  
on team.id = team_id;

select t.name, p.name 
from team as t 
join players as p 
on t.id = team_id;

select t.name as Club, p.name as player
from team as t 
join players as p 
on t.id = team_id;