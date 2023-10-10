CREATE TABLE IF NOT EXISTS shelter(
id INT8 PRIMARY KEY NOT NULL,
shelter_name VARCHAR,
pets_species VARCHAR,
city VARCHAR,
address VARCHAR,
phone_number VARCHAR,
path_description VARCHAR
);

CREATE SEQUENCE shelter_sequence START 2 INCREMENT 1;

