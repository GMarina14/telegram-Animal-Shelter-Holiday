package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Adopter;

import java.util.Collection;
import java.util.Optional;

public interface AdopterService {

    Adopter addNewAdopter (Adopter adopter);

    Collection<Adopter> getAllAdopters();

    Adopter update(Adopter adopter);

    Optional<Adopter> getById(Long id);


    void removeAdopterById(Long id);
}
