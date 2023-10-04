CREATE TABLE  IF NOT EXISTS client (
id INT8 PRIMARY KEY NOT NULL,
chat_id INT8,
userName Varchar,
phoneNumber VARCHAR,
shelter_id INT8 REFERENCES shelter(id)
);
CREATE SEQUENCE client_sequence START 1 INCREMENT 1;
