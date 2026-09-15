create table user_departments
(
    uuid          binary(16) default (uuid_to_bin(16)) not null
        primary key,
    department_id binary(16)                           not null,
    user_id       binary(16)                           not null,
    constraint user_departments_departments_uuid_fk
        foreign key (department_id) references departments (uuid)
            on delete cascade,
    constraint user_departments_users_uuid_fk
        foreign key (user_id) references users (uuid)
            on delete cascade
);

