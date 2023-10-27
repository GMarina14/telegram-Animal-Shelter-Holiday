CREATE TABLE IF NOT EXISTS adopter(
id INT8 PRIMARY KEY NOT NULL,
first_name VARCHAR(50),
last_name VARCHAR(50),
chat_id INT8,
user_name VARCHAR,
phone_number VARCHAR,
volunteer_id INT8 REFERENCES volunteer(id)
);
CREATE SEQUENCE adopter_sequence START 2 INCREMENT 1;

INSERT INTO adopter(id, first_name, last_name, chat_id, user_name, phone_number, volunteer_id)
VALUES (1,'Marina' ,'Gudova',250478712,'gmarina14','8967720998',1);
