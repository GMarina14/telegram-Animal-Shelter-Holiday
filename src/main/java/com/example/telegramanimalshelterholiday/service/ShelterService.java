package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Client;
import com.example.telegramanimalshelterholiday.model.Shelter;
import com.example.telegramanimalshelterholiday.model.Volunteer;

import java.util.List;

public interface ShelterService {
    Shelter getById(long id);

    Shelter add(Shelter shelter);

    Shelter update(Shelter shelter);

    List<Animal> getAllAnimal(long id);

    List<Volunteer> getAllVolunteer(long id);

    List<Client> getAllClient(long id);
}
