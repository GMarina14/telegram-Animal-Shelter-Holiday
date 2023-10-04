CREATE TABLE IF NOT EXISTS animal(
id INT8 PRIMARY KEY NOT NULL,
nick_name VARCHAR(50),
age INT,
pets_speсies VARCHAR,
sex VARCHAR,
health VARCHAR,
shelter_id INT8 REFERENCES shelter(id)
);
CREATE SEQUENCE animal_sequence START 1 INCREMENT 1;

