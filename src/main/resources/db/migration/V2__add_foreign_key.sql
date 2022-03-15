alter table users
    add foreign key (logbook_id) references logbook (id);
    alter table users
    add foreign key (services_id) references services (id);