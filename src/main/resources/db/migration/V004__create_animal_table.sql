CREATE TABLE IF NOT EXISTS animal(
id INT8 PRIMARY KEY NOT NULL,
nick_name VARCHAR(50),
age INT,
pets_species VARCHAR,
sex VARCHAR,
health VARCHAR,
shelter_id INT8 REFERENCES shelter(id)
);
CREATE SEQUENCE animal_sequence START 12 INCREMENT 1;

