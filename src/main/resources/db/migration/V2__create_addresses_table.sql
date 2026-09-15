create table addresses
(
    uuid              binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    building_number   int                                      not null,
    district          varchar(50)                              null,
    street            varchar(50)                              not null,
    city              varchar(50)                              not null,
    state_or_province varchar(50)                              null,
    postal_code       int                                      not null,
    country_code      varchar(5)                               not null,
    user_id           binary(16)                               not null,
    constraint addresses_users_uuid_fk
        foreign key (user_id) references users (uuid)
            on delete cascade
);

