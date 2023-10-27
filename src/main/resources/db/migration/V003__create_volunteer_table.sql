CREATE TABLE IF NOT EXISTS volunteer(
id INT8 PRIMARY KEY NOT NULL,
volunteer_name VARCHAR(50),
chat_id INT8,
shelter_id INT8 REFERENCES shelter (id)
);
CREATE SEQUENCE volunteer_sequence START 2 INCREMENT 1;
INSERT INTO volunteer(id,volunteer_name,chat_id)
VALUES(1,'HanaMounties',1791039547);






