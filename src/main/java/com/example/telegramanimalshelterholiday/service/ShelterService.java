package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Animal;
import com.example.telegramanimalshelterholiday.model.Shelter;

import java.util.List;

public interface ShelterService {
    Shelter getById(long id);

    Shelter add(Shelter shelter);

    Shelter update(Shelter shelter);

    List<Animal> getAllAnimal(long id);
}
