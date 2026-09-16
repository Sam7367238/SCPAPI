alter table user_departments
    add joined datetime default (now()) not null;
