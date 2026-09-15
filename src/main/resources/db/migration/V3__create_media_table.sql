create table media
(
    uuid             binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    file_name        varchar(255)                             not null,
    stored_file_name varchar(255)                             not null,
    mime_type        varchar(5)                               not null,
    size             bigint                                   not null,
    created          datetime   default (now())               null
);

