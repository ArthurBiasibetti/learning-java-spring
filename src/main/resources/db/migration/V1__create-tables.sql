create table users(
    id serial not null,
    name varchar(50) not null,

    primary key(id)
);

create table pets(
    id serial not null,
    owner_id integer not null,
    name varchar(50) not null,

    primary key(id),
    foreign key(owner_id) references users
);

create table schedules(
    id serial not null,
    pet_id integer not null,
    schedule_date date,

    primary key(id),
    foreign key(pet_id) references pets
);