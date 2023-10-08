CREATE TABLE IF NOT EXISTS volunteer(
id INT8 PRIMARY KEY NOT NULL,
volunteer_name VARCHAR(50),
chat_id INT8,
shelter_id INT8 REFERENCES shelter (id)
);
CREATE SEQUENCE volunteer_sequence START 1 INCREMENT 1;






