CREATE TABLE logbook(
ID varchar  primary key auto_increment,
LOG varchar (255)
);
CREATE TABLE users(
ID varchar  primary key auto_increment,
LOGIN varchar (255),
PASSWORD varchar (255),
ACTIVE bit,
LOGBOOK_ID varchar (255),
SERVICES_ID varchar (255)
);
CREATE TABLE services(
ID varchar  primary key auto_increment,
URL varchar (255)
);