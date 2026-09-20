alter table users
    add constraint users_uni
        unique (email);

