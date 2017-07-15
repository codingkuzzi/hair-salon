create table stylists
(
  id serial primary key,
  lastName varchar(250) not null,
  firstName varchar(250) not null
);

create table clients
(
  id serial primary key,
  lastName varchar(250) not null,
  firstName varchar(250) not null,
  gender char(1) not null,
  dateOfBirth date not null,
  stylistId integer references stylists(id)
);

create table appointments
(
  id serial primary key,
  clientId integer not null references clients(id),
  stylistId integer not null references stylists(id),
  dateandtime timestamp not null
);