create table teams
(
    id   bigint auto_increment,
    name varchar(255),
    primary key (id)
);


alter table players
    add team_id bigint,
    add constraint foreign key (team_id) references teams (id);