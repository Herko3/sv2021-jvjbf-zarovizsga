create table players(
    id bigint auto_increment,
    name varchar(255) not null,
    birth_date date,
    position varchar(30),
    primary key (id)
)