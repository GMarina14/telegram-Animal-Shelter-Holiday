package com.example.telegramanimalshelterholiday.service;

import com.example.telegramanimalshelterholiday.model.Adopter;

import java.util.Collection;

public interface AdopterService {

    Adopter addNewAdopter (Adopter adopter);

    Collection<Adopter> getAllAdopters();

    Adopter getAdopterByPhoneNumber(String phoneNumber);

    void removeAdopterById(Long id);
}
