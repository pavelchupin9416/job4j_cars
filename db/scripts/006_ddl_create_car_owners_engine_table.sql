create table engine(
    id serial primary key,
    name varchar not null
);

create table car(
    id serial primary key,
    name varchar not null,
    engine_id int not null unique references engine(id)
    brand_id int not null references brand(id)
);

create table owners(
    id serial primary key,
    name varchar not null,
    user_id int not null references auto_user(id)
);

create table history_owners(
    id   serial primary key,
    car_id int REFERENCES car(id)    NOT NULL,
    owner_id int REFERENCES owners(id)    NOT NULL,
    startAI timestamp,
    endAt timestamp,
    unique (car_id, owner_id)
);
