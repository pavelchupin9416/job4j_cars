create table participate(
    id   serial primary key,
    user_id int REFERENCES auto_user(id)    NOT NULL,
    post_id int REFERENCES auto_post(id)    NOT NULL,
    unique (user_id, post_id)
);