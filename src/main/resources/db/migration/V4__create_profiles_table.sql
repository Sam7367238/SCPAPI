create table profiles
(
    uuid     binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    image_id binary(16)                             null,
    user_id  binary(16)                             not null,

    constraint profiles_media_uuid_fk
        foreign key (image_id) references media (uuid)
            on delete set null,
    constraint profiles_users_uuid_fk
        foreign key (user_id) references users (uuid)
);

