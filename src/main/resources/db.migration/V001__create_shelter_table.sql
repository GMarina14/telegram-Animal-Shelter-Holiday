CREATE TABLE IF NOT EXISTS shelter(
shelter_id INT8 PRIMARY KEY NOT NULL,
shelter_name VARCHAR,
pets_species VARCHAR,
city VARCHAR,
address VARCHAR,
phone_number VARCHAR,
path_description VARCHAR
);

CREATE SEQUENCE shelter_sequence START 1 INCREMENT 1;


INSERT INTO shelter(shelter_id, shelter_name, pets_species, city, address, phone_number, path_description)
VALUES (1,'ShelterDog','DOG','Astana','улица Аккорган, 5В','+77172555555','https://yandex.ru/maps/163/astana/house/Y0gYdQRlTkABQFtrfX12d39kZA==/?ll=71.191489%2C51.176370&mode=search&sll=71.486705%2C51.140482&sspn=0.383348%2C0.168229&text=приют&z=10.87'),
       (2,'SheltonCat','CAT','Moscow','ул.Большая Полянка,57','+79109109191','https://yandex.ru/maps/org/priyut_dlya_sobak_i_koshek/205962911849/?ll=37.621965%2C55.731298&mode=search&sctx=ZAAAAAgBEAAaKAoSCS5zuiwm31FAEZ3WbVD7kUlAEhIJJCu%2FDMaI2D8ReZCeIoeIxT8iBgABAgMEBSgKOABAowFIAWIqY29sbGVjdGlvbnNfcmFua2luZ19tb2RlbD1jb2xsZWN0aW9uc19kc3NtagJrep0BzcxMPaABAKgBAL0BALM%2B18IBIs2orbiyApjZhLXfBqrl0fwE84Ct%2BLYD2u2jngqejLbY%2BAPqAQDyAQD4AQCCAgrQv9GA0LjRjtGCigIVMTYwNTgyMjU3MzgkMTg0MTA2MzA2kgIAmgIMZGVza3RvcC1tYXBz&sll=37.621965%2C55.731298&sspn=0.012316%2C0.004848&text=приют&z=16.02');