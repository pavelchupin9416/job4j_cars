CREATE TABLE auto_post
(
    id SERIAL PRIMARY KEY,
    description varchar not null,
    created timestamp,
    auto_user_id int references auto_user(id),
    car_id int references car(id),
    file_id int references files(id)
);
