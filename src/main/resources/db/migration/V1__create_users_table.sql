create table users
(
    uuid            binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    name            VARCHAR(150)                             not null,
    phone_number    VARCHAR(25)                              not null,
    clearance_level tinyint    default 0                     not null,
    password VARCHAR(255) NULL,
    created         datetime   default (NOW())               not null
);

