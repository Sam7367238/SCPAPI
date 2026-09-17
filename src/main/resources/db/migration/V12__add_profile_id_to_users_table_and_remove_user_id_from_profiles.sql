alter table users
    add profile_id binary(16) not null;

alter table users
    add constraint users_profiles_uuid_fk
        foreign key (profile_id) references profiles (uuid);

alter table profiles
    drop foreign key profiles_users_uuid_fk;

drop index profiles_users_uuid_fk on profiles;

alter table profiles
    drop column user_id;

