create TABLE IF NOT EXISTS shelter(
id INT8 PRIMARY KEY NOT NULL,
shelter_name VARCHAR,
pets_species VARCHAR,
country VARCHAR,
city VARCHAR,
address VARCHAR,
phone_number VARCHAR,
path_description VARCHAR
);

create sequence shelter_sequence start 3 increment 1;

