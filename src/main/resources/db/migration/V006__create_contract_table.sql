CREATE TABLE IF NOT EXISTS contract(
id INT8 PRIMARY KEY NOT NULL,
contract_data DATE,
chat_id INT8,
probation_period INT,
animal_id INT8 REFERENCES animal(id),
 adopter_id INT8 REFERENCES adopter(id)
);

CREATE SEQUENCE contract_sequence START 5 INCREMENT 1;
