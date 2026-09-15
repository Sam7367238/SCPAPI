create table departments
(
    uuid     binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    name     varchar(255)                             not null,
    image_id binary(16)                                      null,
    user_id  binary(16)                               null,
    constraint departments_media_uuid_fk
        foreign key (image_id) references media (uuid) on delete set null,
    constraint departments_users_uuid_fk
        foreign key (user_id) references users (uuid) on delete set null
);

