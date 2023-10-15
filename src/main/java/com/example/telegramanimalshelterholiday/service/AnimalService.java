package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.constants.enums.PetsSpecies;
import com.example.telegramanimalshelterholiday.model.Animal;

import java.util.List;

public interface AnimalService {
    Animal getById(long id);

    Animal add(Animal animal);

    Animal update(Animal animal);

    void remove(long id);

    List<Animal> getAnimalByPetsSpecies(PetsSpecies petsSpecies);
}
