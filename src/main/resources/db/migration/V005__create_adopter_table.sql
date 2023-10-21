CREATE TABLE IF NOT EXISTS adopter(
id INT8 PRIMARY KEY NOT NULL,
first_name VARCHAR(50),
last_name VARCHAR(50),
prob_extend INT,
chat_id INT8,
user_name VARCHAR,
phone_number VARCHAR,
volunteer_id INT8 REFERENCES volunteer(id)
);
CREATE SEQUENCE adopter_sequence START 1 INCREMENT 1;