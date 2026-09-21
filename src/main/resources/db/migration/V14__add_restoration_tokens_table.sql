create table restoration_tokens
(
    uuid       binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    purpose    VARCHAR(255)                       not null,
    user_id    binary(16)                         not null,
    activated  boolean    default (false)         not null,
    expiration datetime                           not null,
    created    datetime   default (now())         not null,
    constraint restoration_tokens_users_uuid_fk
        foreign key (user_id) references users (uuid)
);

