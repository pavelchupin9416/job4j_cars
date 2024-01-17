create table brand(
    id serial primary key,
    name varchar not null
);

create table files
(
    id   serial primary key,
    name varchar not null,
    path varchar not null unique
);