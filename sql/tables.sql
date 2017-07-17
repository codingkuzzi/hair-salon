CREATE TABLE stylists
(
  id serial PRIMARY KEY,
  lastName varchar(250) NOT NULL,
  firstName varchar(250) NOT NULL
);

CREATE TABLE clients
(
  id serial PRIMARY KEY,
  lastName varchar(250) NOT NULL,
  firstName varchar(250) NOT NULL,
  gender char(1) NOT NULL,
  dateOfBirth date NOT NULL,
  stylistId integer REFERENCES stylists(id)
);

CREATE TABLE appointments
(
  id serial PRIMARY KEY,
  clientId integer NOT NULL REFERENCES clients(id),
  stylistId integer NOT NULL REFERENCES stylists(id),
  dateAndTime timestamp NOT NULL
);