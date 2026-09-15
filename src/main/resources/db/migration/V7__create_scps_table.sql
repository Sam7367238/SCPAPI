create table scps
(
    uuid            binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    clearance_level tinyint                                  not null,
    title           varchar(255)                             not null,
    description     text                                     not null,
    image_id        binary(16)                               null,
    user_id         binary(16)                               null,
    created         datetime   default (now())               null,
    constraint scps_media_uuid_fk
        foreign key (image_id) references media (uuid)
            on delete set null,
    constraint scps_users_uuid_fk
        foreign key (user_id) references users (uuid)
            on delete set null
);

