
CREATE TABLE IF NOT EXISTS report(
id INT8 PRIMARY KEY NOT NULL,
report_data DATE,
chat_id INT8,
photo bytea,
diet VARCHAR,
health VARCHAR,
behavior VARCHAR,
contract_id INT8 REFERENCES contract(id)
);

CREATE SEQUENCE report_sequence START 1 INCREMENT 1;
